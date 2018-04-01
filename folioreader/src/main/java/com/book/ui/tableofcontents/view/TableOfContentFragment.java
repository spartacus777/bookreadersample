package com.book.ui.tableofcontents.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.book.Config;
import com.book.Constants;
import com.book.R;
import com.book.model.TOCLinkWrapper;
import com.book.ui.tableofcontents.adapter.TOCAdapter;
import com.book.ui.tableofcontents.presenter.TOCMvpView;
import com.book.ui.tableofcontents.presenter.TableOfContentsPresenter;
import com.book.util.AppUtil;

import java.util.ArrayList;

import static com.book.Constants.BOOK_TITLE;
import static com.book.Constants.CHAPTER_SELECTED;
import static com.book.Constants.SELECTED_CHAPTER_POSITION;
import static com.book.Constants.TYPE;

public class TableOfContentFragment extends Fragment implements TOCMvpView, TOCAdapter.TOCCallback {
    private TOCAdapter mTOCAdapter;
    private RecyclerView mTableOfContentsRecyclerView;
    private TableOfContentsPresenter presenter;
    private TextView errorView;
    private Config mConfig;
    private String mBookTitle;

    public static TableOfContentFragment newInstance(String selectedChapterHref, String bookTitle) {
        TableOfContentFragment tableOfContentFragment = new TableOfContentFragment();
        Bundle args = new Bundle();
        args.putString(SELECTED_CHAPTER_POSITION, selectedChapterHref);
        args.putString(BOOK_TITLE, bookTitle);
        tableOfContentFragment.setArguments(args);
        return tableOfContentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TableOfContentsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_contents, container, false);
        mConfig = AppUtil.getSavedConfig(getActivity());
        mBookTitle = getArguments().getString(BOOK_TITLE);
        if (mConfig.isNightMode()) {
            mRootView.findViewById(R.id.recycler_view_menu).
                    setBackgroundColor(ContextCompat.getColor(getActivity(),
                            R.color.black));
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTableOfContentsRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_menu);
        errorView = (TextView) view.findViewById(R.id.tv_error);
        String urlString = Constants.LOCALHOST + mBookTitle + "/manifest";

        configRecyclerViews();
        presenter.getTOCContent(urlString);
    }

    public void configRecyclerViews() {
        mTableOfContentsRecyclerView.setHasFixedSize(true);
        mTableOfContentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mTableOfContentsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onLoadTOC(ArrayList<TOCLinkWrapper> tocLinkWrapperList) {
        mTOCAdapter = new TOCAdapter(getActivity(), tocLinkWrapperList, getArguments().getString(SELECTED_CHAPTER_POSITION), mConfig);
        mTOCAdapter.setCallback(this);
        mTableOfContentsRecyclerView.setAdapter(mTOCAdapter);
    }

    @Override
    public void onError() {
        errorView.setVisibility(View.VISIBLE);
        mTableOfContentsRecyclerView.setVisibility(View.GONE);
        errorView.setText("Table of content \n not found");
    }

    @Override
    public void onTocClicked(int position) {
        TOCLinkWrapper tocLinkWrapper = (TOCLinkWrapper) mTOCAdapter.getItemAt(position);
        Intent intent = new Intent();
        intent.putExtra(SELECTED_CHAPTER_POSITION, tocLinkWrapper.getTocLink().href);
        intent.putExtra(BOOK_TITLE, tocLinkWrapper.getTocLink().bookTitle);
        intent.putExtra(TYPE, CHAPTER_SELECTED);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void onExpanded(int position) {
        TOCLinkWrapper tocLinkWrapper = (TOCLinkWrapper) mTOCAdapter.getItemAt(position);
        if (tocLinkWrapper.getChildren() != null && tocLinkWrapper.getChildren().size() > 0) {
            mTOCAdapter.toggleGroup(position);
        }
    }
}
package com.book.ui.tableofcontents.presenter;

import com.book.model.TOCLinkWrapper;
import com.book.ui.base.BaseMvpView;

import java.util.ArrayList;

/**
 * @author gautam chibde on 8/6/17.
 */

public interface TOCMvpView extends BaseMvpView {

    void onLoadTOC(ArrayList<TOCLinkWrapper> tocLinkWrapperList);
}

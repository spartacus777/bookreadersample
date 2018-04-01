package com.book.ui.folio.presenter;

import com.book.ui.base.BaseMvpView;

import org.readium.r2_streamer.model.publication.EpubPublication;

/**
 * @author gautam chibde on 8/6/17.
 */

public interface MainMvpView extends BaseMvpView {
    void onLoadPublication(EpubPublication publication);
}
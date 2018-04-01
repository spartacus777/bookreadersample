package com.book.ui.base;

import com.book.model.dictionary.Wikipedia;

/**
 * @author gautam chibde on 4/7/17.
 */

public interface WikipediaCallBack extends BaseMvpView {

    void onWikipediaDataReceived(Wikipedia wikipedia);

    //TODO
    void playMedia(String url);
}

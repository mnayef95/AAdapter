package com.example.mohamed.advanceadapter.listener;

import android.view.View;

/**
 * AdvanceAdapter
 * Created by Mohamed Hamdan on 2017-May-18.
 */

public interface OnItemClickListener {

    void onItemClick(View view, boolean isHeader, boolean isFooter, int hfPosition, int position);

}

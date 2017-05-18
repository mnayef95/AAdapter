package com.mohamed.aadapter.listener;

import android.view.View;

/**
 * AdvanceAdapter
 * Created by Mohamed Hamdan on 2017-May-18.
 */

public interface OnItemClickListener {

    void onItemClick(View view, boolean isHeader, boolean isFooter, int hfPosition, int position);

}

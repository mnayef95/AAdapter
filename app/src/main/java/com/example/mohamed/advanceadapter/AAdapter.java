package com.example.mohamed.advanceadapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohamed.advanceadapter.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * AdvanceAdapter
 * Created by Mohamed Hamdan on 2017-May-18.
 */

public class AAdapter extends Adapter<ViewHolder> {

    private final static int FOOTER_TYPE = 0;
    private final static int HEADER_TYPE = 1;
    private final static int OTHERS_TYPE = 2;

    private OnItemClickListener onItemClickListener;

    private View footer;
    private View header;

    private ArrayList<View> footers = new ArrayList<>();
    private ArrayList<View> headers = new ArrayList<>();

    private Adapter adapter;

    public AAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < headers.size() && headers.size() > 0) {
            header = headers.get(position);
            return HEADER_TYPE;
        } else if (footers.size() > 0 && (position >= (adapter.getItemCount() + headers.size()))) {
            footer = footers.get(position - (adapter.getItemCount() + headers.size()));
            return FOOTER_TYPE;
        } else {
            return OTHERS_TYPE;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        switch (viewType) {
            case HEADER_TYPE: {
                viewHolder = new HeaderHolder(header);
                break;
            }
            case FOOTER_TYPE: {
                viewHolder = new FooterHolder(footer);
                break;
            }
            default: {
                viewHolder = adapter.onCreateViewHolder(parent, viewType);
                break;
            }
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case FOOTER_TYPE: {
                break;
            }
            case HEADER_TYPE: {
                break;
            }
            case OTHERS_TYPE: {
                adapter.onBindViewHolder(holder, position);
                break;
            }
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        switch (getItemViewType(position)) {
            case FOOTER_TYPE: {
                break;
            }
            case HEADER_TYPE: {
                break;
            }
            case OTHERS_TYPE: {
                adapter.onBindViewHolder(holder, position, payloads);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount() + footers.size() + headers.size();
    }

    public void addHeader(View header) {
        headers.add(header);
    }

    public void removeHeader(View header) {
        headers.remove(header);
    }

    public int getHeadersCount() {
        return headers.size();
    }

    public boolean hasHeaders() {
        return headers.size() > 0;
    }

    public void addFooter(View footer) {
        footers.add(footer);
    }

    public void removeFooter(View footer) {
        footers.remove(footer);
    }

    public int getFootersCount() {
        return footers.size();
    }

    public boolean hasFooters() {
        return footers.size() > 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    private class FooterHolder extends ViewHolder implements View.OnClickListener {

        private FooterHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, false, true, 0, getAdapterPosition());
            }
        }
    }

    private class HeaderHolder extends ViewHolder implements View.OnClickListener {

        private HeaderHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, true, false, 0, getAdapterPosition());
            }
        }
    }
}

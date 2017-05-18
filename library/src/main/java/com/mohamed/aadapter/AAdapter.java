package com.mohamed.aadapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

import com.mohamed.aadapter.listener.OnItemClickListener;
import com.mohamed.aadapter.model.Footer;
import com.mohamed.aadapter.model.Header;

import java.util.ArrayList;

/**
 * AAdapter
 * Created by Mohamed Hamdan on 2017-May-18.
 */

@SuppressWarnings({"unused", "WeakerAccess", "unchecked"})
public class AAdapter extends Adapter<ViewHolder> implements RecyclerView.OnChildAttachStateChangeListener {

    private final static int FOOTER_TYPE = 0;
    private final static int HEADER_TYPE = 1;
    private final static int OTHERS_TYPE = 2;

    private RecyclerView recyclerView;
    private OnItemClickListener onItemClickListener;

    private View footer;
    private View header;

    private ArrayList<View> footers = new ArrayList<>();
    private ArrayList<View> headers = new ArrayList<>();

    private ArrayList<Footer> footerModels = new ArrayList<>();
    private ArrayList<Header> headerModels = new ArrayList<>();

    private Adapter adapter;

    public AAdapter(Adapter adapter) {
        this.adapter = adapter;
        adapter.setHasStableIds(true);
        setHasStableIds(true);
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case FOOTER_TYPE: {
                int footerPosition = position - (adapter.getItemCount() + headers.size());
                footerModels.get(footerPosition).setPosition(footerPosition);
                break;
            }
            case HEADER_TYPE: {
                headerModels.get(position).setPosition(position);
                break;
            }
            case OTHERS_TYPE: {
                try {
                    adapter.onBindViewHolder(holder, position);
                } catch (Exception ignored) {
                }
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount() + footers.size() + headers.size();
    }

    public void addHeader(View header) {
        if (headers.contains(footer)) {
            throw new RuntimeException("Header already exist: " + header.toString());
        } else {
            headers.add(header);
            headerModels.add(new Header());
        }
    }

    public void removeHeader(View header) {
        int position = headers.indexOf(footer);
        if (position != -1) {
            headers.remove(header);
            headerModels.remove(position);
        }
    }

    public int getHeadersCount() {
        return headers.size();
    }

    public boolean hasHeaders() {
        return headers.size() > 0;
    }

    public void removeAllHeaders() {
        headers.clear();
        headerModels.clear();
    }

    public void addFooter(View footer) {
        if (footers.contains(footer)) {
            throw new RuntimeException("Footer already exist: " + footer.toString());
        } else {
            footers.add(footer);
            footerModels.add(new Footer());
        }
    }

    public void removeFooter(View footer) {
        int position = footers.indexOf(footer);
        if (position != -1) {
            footerModels.remove(position);
            footers.remove(footer);
        }
    }

    public int getFootersCount() {
        return footers.size();
    }

    public boolean hasFooters() {
        return footers.size() > 0;
    }

    public void removeAllFooters() {
        footers.clear();
        footerModels.clear();
    }

    public void setOnItemClickListener(RecyclerView recyclerView, OnItemClickListener listener) {
        this.onItemClickListener = listener;
        this.recyclerView = recyclerView;

        recyclerView.addOnChildAttachStateChangeListener(this);
    }

    public void removeOnItemClickListener() {
        if (recyclerView != null) {
            detach(recyclerView);
        }
        onItemClickListener = null;
    }

    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(this);
    }

    @Override
    public void onChildViewAttachedToWindow(View view) {
        if (onItemClickListener != null) {
            view.setOnClickListener(mOnClickListener);
        }
    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {

    }

    private class FooterHolder extends ViewHolder {

        private FooterHolder(View itemView) {
            super(itemView);
        }
    }

    private class HeaderHolder extends ViewHolder {

        private HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                int position = holder.getAdapterPosition();
                if (position != -1) {
                    switch (holder.getItemViewType()) {
                        case FOOTER_TYPE: {
                            int footerPosition = position - (adapter.getItemCount() + headers.size());
                            onItemClickListener.onItemClick(v, false, true, footerModels.get(footerPosition).getPosition(), position);
                            break;
                        }
                        case HEADER_TYPE: {
                            onItemClickListener.onItemClick(v, true, false, headerModels.get(position).getPosition(), position);
                            break;
                        }
                        default: {
                            onItemClickListener.onItemClick(v, false, false, position - headers.size(), position);
                            break;
                        }
                    }
                }
            }
        }
    };
}

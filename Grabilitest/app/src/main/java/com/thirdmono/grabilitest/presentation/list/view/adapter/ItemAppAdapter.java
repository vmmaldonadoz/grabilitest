package com.thirdmono.grabilitest.presentation.list.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thirdmono.grabilitest.R;
import com.thirdmono.grabilitest.data.entity.Entry;
import com.thirdmono.grabilitest.data.entity.ImImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link android.support.v7.widget.RecyclerView.Adapter} for the Apps list.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class ItemAppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Entry> items;
    private Context context;
    private OnItemClickListener clickListener;

    public ItemAppAdapter(List<Entry> list, OnItemClickListener listener, Context context) {
        this.items = list;
        this.clickListener = listener;
        this.context = context;
    }

    public void setItems(List<Entry> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_app_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolderItem viewHolderBody = (ViewHolderItem) holder;
        final Entry item = items.get(position);

        final List<ImImage> images = item.getImImage();
        if (images.size() > 0) {
            Picasso.with(context)
                    .load(images.get(images.size() - 1).getLabel())
                    .into(viewHolderBody.itemIcon);
        }

        viewHolderBody.itemTitle.setText(item.getImName().getLabel());
        viewHolderBody.itemArtist.setText(item.getImArtist().getLabel());
        viewHolderBody.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(view, item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener {
        void onClick(View view, Entry entry);
    }

    static class ViewHolderItem extends RecyclerView.ViewHolder {
        @BindView(R.id.item_icon)
        ImageView itemIcon;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_artist)
        TextView itemArtist;

        ViewHolderItem(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}

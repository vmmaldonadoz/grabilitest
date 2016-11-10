package com.thirdmono.grabilitest.presentation.details.view;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thirdmono.grabilitest.AppStoreApplication;
import com.thirdmono.grabilitest.R;
import com.thirdmono.grabilitest.data.entity.Entry;
import com.thirdmono.grabilitest.domain.utils.Constants;
import com.thirdmono.grabilitest.domain.utils.Utils;
import com.thirdmono.grabilitest.presentation.details.DetailsContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * View for the application details.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class DetailsFragment extends Fragment implements DetailsContract.View {

    @Inject
    DetailsContract.Presenter presenter;

    @BindView(R.id.item_icon)
    ImageView itemIcon;
    @BindView(R.id.item_artist)
    TextView itemArtist;
    @BindView(R.id.item_category)
    TextView itemCategory;
    @BindView(R.id.item_title)
    TextView itemTitle;
    @BindView(R.id.item_description)
    TextView itemDescription;

    public DetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDependencyInjection();

        Entry entry = (getArguments() != null && getArguments().containsKey(Constants.APP_SELECTED_KEY)) ?
                Utils.valueOf(getArguments().getString(Constants.APP_SELECTED_KEY)) :
                null;
        presenter.setView(this);
        presenter.setEntry(entry);
    }

    private void setupDependencyInjection() {
        ((AppStoreApplication) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        presenter.init();
        return view;
    }

    @Override
    public void registerConnectionBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unRegisterConnectionBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void hideNoConnectionMessage() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showNoConnectionMessage() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTitle(String label) {
        itemTitle.setText(label);
    }

    @Override
    public void setArtist(String label) {
        itemArtist.setText(label);
    }

    @Override
    public void setCategory(String label) {
        itemCategory.setText(label);
    }

    @Override
    public void setDescription(String label) {
        itemDescription.setText(label);
    }

    @Override
    public void setIcon(String label) {
        Picasso.with(getActivity()).load(label).into(itemIcon);
    }

}

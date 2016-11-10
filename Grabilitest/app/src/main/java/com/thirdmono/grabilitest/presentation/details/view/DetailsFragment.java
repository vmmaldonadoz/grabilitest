package com.thirdmono.grabilitest.presentation.details.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thirdmono.grabilitest.AppStoreApplication;
import com.thirdmono.grabilitest.R;
import com.thirdmono.grabilitest.presentation.details.AppDetailsContract;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * View for the application details.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class DetailsFragment extends Fragment implements AppDetailsContract.View {

    @Inject
    AppDetailsContract.Presenter presenter;

    private OnFragmentInteractionListener listener;

    public DetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDependencyInjection();
        if (getArguments() != null) {

        }
    }

    private void setupDependencyInjection() {
        ((AppStoreApplication) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

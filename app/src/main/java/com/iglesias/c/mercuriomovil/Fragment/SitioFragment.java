package com.iglesias.c.mercuriomovil.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iglesias.c.mercuriomovil.Adapter.RecyclerAdapter;
import com.iglesias.c.mercuriomovil.ItemDecoration.SpacingDecoration;
import com.iglesias.c.mercuriomovil.Pojo.SitioItem;
import com.iglesias.c.mercuriomovil.Presenter.SitioPresenterImpl;
import com.iglesias.c.mercuriomovil.R;
import com.iglesias.c.mercuriomovil.View.FragmentRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class SitioFragment extends Fragment implements FragmentRecyclerView {
    RecyclerView recyclerView;
    SitioPresenterImpl presenter;
    RecyclerAdapter adapter;
    private ProgressDialog loading;


    public SitioFragment() {
    }


    private void setupLoading() {
        loading = new ProgressDialog(getContext());
        loading.setCancelable(false);
        loading.setMessage(getResources().getString(R.string.str_msj_loading_sitios));
    }


    public void generarLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpacingDecoration(spacingInPixels));
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    public void initAdapterRV() {
        ArrayList<SitioItem> listItems = new ArrayList<>();
        adapter = new RecyclerAdapter(listItems, getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sitio_list, container, false);


        recyclerView = view.findViewById(R.id.id_recyclerview_list_items);
        presenter = new SitioPresenterImpl(this);
        generarLinearLayout();
        initAdapterRV();
        setupLoading();
        presenter.getListItems();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.str_title_alert_error_sitios);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void updateList(List<SitioItem> listItems) {
        adapter.updateListSitios(listItems);
    }

    @Override
    public void showLoading() {
        if (loading != null && !loading.isShowing()) {
            loading.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }

    @Override
    public void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.myDialog);

        builder.setTitle(getResources().getString(R.string.str_title_alert_error_sitios));
        builder.setMessage(getResources().getString(R.string.str_msj_alert_error_sitios));
        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public Context getContextActivity() {
        return getContext();
    }


}

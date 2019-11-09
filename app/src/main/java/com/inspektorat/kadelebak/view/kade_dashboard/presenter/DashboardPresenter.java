package com.inspektorat.kadelebak.view.kade_dashboard.presenter;

import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.kade_dashboard.view.DashboardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardPresenter {

    private DashboardView.Fitur viewFitur;
    private String[] listFitur;

    public DashboardPresenter(DashboardView.Fitur viewFitur) {
        this.viewFitur = viewFitur;
    }

    public void setFitur(String[] listFitur) {
        this.listFitur = listFitur;
        viewFitur.showDataFitur(Arrays.asList(listFitur));
    }
}

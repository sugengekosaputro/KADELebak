package com.inspektorat.kadelebak.view.kade_complaint.presenter;

import com.inspektorat.kadelebak.view.kade_complaint.view.ComplaintView;

import java.util.Arrays;

public class ComplaintPresenter {

    private ComplaintView.Fitur viewFitur;
    private String[] listFitur;

    public ComplaintPresenter(ComplaintView.Fitur viewFitur) {
        this.viewFitur = viewFitur;
    }

    public void setFitur(String[] listFitur) {
        this.listFitur = listFitur;
        viewFitur.showDataFitur(Arrays.asList(listFitur));
    }
}

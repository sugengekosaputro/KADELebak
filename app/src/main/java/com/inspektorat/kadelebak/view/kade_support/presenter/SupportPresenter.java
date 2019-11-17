package com.inspektorat.kadelebak.view.kade_support.presenter;

import com.inspektorat.kadelebak.view.kade_support.view.SupportVew;

import java.util.Arrays;

public class SupportPresenter {

    private SupportVew.Fitur viewFitur;
    private String[] listFitur;

    public SupportPresenter(SupportVew.Fitur viewFitur) {
        this.viewFitur = viewFitur;
    }

    public void setFitur(String[] listFitur) {
        this.listFitur = listFitur;
        viewFitur.showDataFitur(Arrays.asList(listFitur));
    }
}

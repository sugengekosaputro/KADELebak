package com.inspektorat.kadelebak.view.kade_support.presenter;

import com.inspektorat.kadelebak.view.kade_support.SupportModel;
import com.inspektorat.kadelebak.view.kade_support.view.SupportVew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SupportPresenter {

    private SupportVew.Fitur viewFitur;

    public SupportPresenter(SupportVew.Fitur viewFitur) {
        this.viewFitur = viewFitur;

        List<SupportModel> supportModels = new ArrayList<>();
        SupportModel m1 = new SupportModel(1,"Cara menggunakan fitur pengaduan","<p>Cara menggunakan fitur pengaduan</p>\n" +
                "<p>1. Buka aplikasi Kade Lebak</p>\n" +
                "<p>2. Pilih tab <strong>Pengaduan</strong></p>\n" +
                "<p>3. Tekan <em>icon</em> dipojok kanan atas </p>\n" +
                "<p>4. Selesai</p>");

        SupportModel m2 = new SupportModel(2,"Fitur Anonymous","<p>Fitur Anonymous</p>\n" +
                "<p>Fitur Anonymous adalah fitur yang dapat menyembunyikan nama anda saat mengirim pengaduan.</p>\n" +
                "<p>Mohon digunakan dengan bijak.</p>\n" +
                "<p>&nbsp;</p>");

        supportModels.add(m1);
        supportModels.add(m2);

        this.viewFitur.showData(supportModels);
    }
}

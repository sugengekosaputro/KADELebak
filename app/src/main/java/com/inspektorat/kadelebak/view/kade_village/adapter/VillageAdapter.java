package com.inspektorat.kadelebak.view.kade_village.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.entity.InstitutionEntity;
import com.inspektorat.kadelebak.view.kade_support.adapter.SupportAdapter;
import com.inspektorat.kadelebak.view.kade_village.entity.Institution;
import com.inspektorat.kadelebak.view.kade_village.presenter.VillagePresenter;
import com.inspektorat.kadelebak.view.kade_village.view.DetailVillageActivity;
import com.inspektorat.kadelebak.view.kade_village.view.VillageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VillageAdapter extends RecyclerView.Adapter<VillageAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<InstitutionEntity> institutionList;
    private List<InstitutionEntity> institutionFilter;
    private VillageView.Detail detail;
    private VillagePresenter presenter;

    public VillageAdapter(Context context, List<InstitutionEntity> institutionList) {
        this.context = context;
        this.institutionList = institutionList;
        this.institutionFilter = new ArrayList<>(institutionList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_village, parent, false);
        return new VillageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InstitutionEntity institution = institutionList.get(position);
        holder.position = position;
        holder.villageName.setText(institution.getName());
        holder.cardView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            Intent intent = new Intent(context, DetailVillageActivity.class);
            bundle.putSerializable(Constant.SERIALIZABLE_VILLAGE, institution);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return (institutionList.size() > 0) ? institutionList.size() : 0;
    }

    private void setViewDetail(VillageView.Detail detail){
        this.detail = detail;
    }

    @Override
    public Filter getFilter() {
        return filterList;
    }

    private Filter filterList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<InstitutionEntity> filterList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filterList.addAll(institutionFilter);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (InstitutionEntity item : institutionFilter) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filterList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            institutionList.clear();
            institutionList.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv_item_village)
        CardView cardView;
        @BindView(R.id.tv_item_village_name)
        TextView villageName;

        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

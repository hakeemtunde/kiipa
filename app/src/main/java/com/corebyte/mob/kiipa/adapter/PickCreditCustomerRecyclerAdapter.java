package com.corebyte.mob.kiipa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.event.PickCreditCustomerEvent;
import com.corebyte.mob.kiipa.model.Customer;

import java.util.List;

public class PickCreditCustomerRecyclerAdapter extends RecyclerView.Adapter<PickCreditCustomerRecyclerAdapter.ViewHolder> {

    private PickCreditCustomerEvent mEventHandler;
    private List<Customer> mCustomers;
    private int mSelectedItem = -1;
    private PickCreditCustomerEvent.OnClickCreditor mOnClickCreditor;


    public PickCreditCustomerRecyclerAdapter(PickCreditCustomerEvent handler, PickCreditCustomerEvent.OnClickCreditor onClickCreditor) {
        mEventHandler = handler;
        mCustomers = handler.fetchAll();
        mOnClickCreditor = onClickCreditor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_credit_customer_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Customer customer = mCustomers.get(position);
        viewHolder.bind(customer, position);
    }

    @Override
    public int getItemCount() {
        return mCustomers == null ? 0 : mCustomers.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNameTv;
        private TextView mPhoneTv;
        private TextView mCreditTv;
        private RadioButton mPickRb;
        View mItemView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameTv = itemView.findViewById(R.id.customer_name_tv);
            mPhoneTv = itemView.findViewById(R.id.customer_phone_tv);
            mCreditTv = itemView.findViewById(R.id.credit_value_tv);
            mPickRb = itemView.findViewById(R.id.pick_credit_customer_rb);
            itemView.setOnClickListener(this);
            mPickRb.setOnClickListener(this);
            mItemView = itemView;

        }

        public void bind(Customer customer, int position) {
            mNameTv.setText(customer.getName());
            mPhoneTv.setText(customer.getPhone());
            mItemView.setBackgroundColor(ContextCompat
                    .getColor(mEventHandler.getContext(), R.color.cardview_light_background));

            mPickRb.setChecked(position == mSelectedItem);
            if(position == mSelectedItem ) {
                mItemView.setBackgroundColor(ContextCompat.getColor(mEventHandler.getContext(), R.color.colorLightGray));;
            }

        }

        @Override
        public void onClick(View view) {
            mSelectedItem = getAdapterPosition();
            mOnClickCreditor.onClick(mCustomers.get(mSelectedItem));
            notifyDataSetChanged();

        }
    }
}
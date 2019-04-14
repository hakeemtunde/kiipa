package com.corebyte.mob.kiipa.event;

import android.content.Context;

import com.corebyte.mob.kiipa.adapter.AdapterDataLoader;
import com.corebyte.mob.kiipa.model.Category;
import com.corebyte.mob.kiipa.repo.CategoryCrudOperation;

public interface StockDialogAction {

    void create(String name);

    void update(Category category);

    void refreshAdapter(AdapterDataLoader adapterDataLoader);

    boolean isValid(String name);

    class StockDialogActionImp implements StockDialogAction {

        private CategoryCrudOperation mCrudOperation;

        public StockDialogActionImp(Context context) {
            mCrudOperation = new CategoryCrudOperation(context);
        }

        @Override
        public void create(String name) {
            mCrudOperation.create(new Category(name, 1));
        }

        @Override
        public void update(Category category) {
            mCrudOperation.update(category);
        }

        @Override
        public void refreshAdapter(AdapterDataLoader adapterDataLoader) {
            if (adapterDataLoader == null) return;
            mCrudOperation.loadDataToAdapter(adapterDataLoader);
        }

        @Override
        public boolean isValid(String name) {
            return true;
        }
    }
}

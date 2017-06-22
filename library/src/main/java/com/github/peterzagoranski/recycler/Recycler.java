package com.github.peterzagoranski.recycler;

import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

@SuppressWarnings("unused")
public class Recycler {
    public static class RecyclerAdapter {

        RecyclerAdapter(@NonNull final RecyclerView.Adapter adapter) {
            this.adapter = adapter;

            this.linear();
        }

        public RecyclerAdapter linear() {
            this.manager = Managers.Linear;

            return this;
        }

        public RecyclerAdapter reverse() {
            this.manager = Managers.Reverse;

            return this;
        }

        public RecyclerAdapter grid(int portrait, int landscape) {
            this.manager = Managers.Grid;
            this.portrait = portrait;
            this.landscape = landscape;

            return this;
        }

        public RecyclerAdapter grid() {
            return grid(portrait, landscape);
        }

        public void into(RecyclerView view) {
            switch (manager) {
                case Reverse: {
                    final LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
                    manager.setStackFromEnd(true);
                    manager.setReverseLayout(true);

                    view.setLayoutManager(manager);
                }
                break;

                case Grid: {
                    final int columns = view.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? portrait : landscape;

                    view.setLayoutManager(new StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL));
                }
                break;

                default: {
                    view.setLayoutManager(new LinearLayoutManager(view.getContext()));
                }
            }

            view.setAdapter(adapter);
        }

        private enum Managers {
            Linear,
            Reverse,
            Grid
        }

        private final RecyclerView.Adapter adapter;
        private Managers manager;
        private int portrait = 2;
        private int landscape = 4;
    }

    public static RecyclerAdapter with(RecyclerView.Adapter adapter) {
        return new RecyclerAdapter(adapter);
    }
}

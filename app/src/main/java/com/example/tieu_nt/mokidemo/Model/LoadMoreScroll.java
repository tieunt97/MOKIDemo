package com.example.tieu_nt.mokidemo.Model;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by tieu_nt on 4/23/2018.
 */

public class LoadMoreScroll extends RecyclerView.OnScrollListener{
    int itemAnDauTien = 0;
    int tongItem = 0;
    int itemLoadTruoc = 4;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore){
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        tongItem = layoutManager.getItemCount();

        if (layoutManager instanceof LinearLayoutManager){
            itemAnDauTien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }else if(layoutManager instanceof GridLayoutManager){
            itemAnDauTien = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

        if(tongItem == (itemAnDauTien + itemLoadTruoc)){
            iLoadMore.loadMore(tongItem);
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}

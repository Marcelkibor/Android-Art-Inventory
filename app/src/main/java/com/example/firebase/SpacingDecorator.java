package com.example.firebase;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacingDecorator extends RecyclerView.ItemDecoration {
    private final int verticalSpace;

    public SpacingDecorator(int verticalSpace) {
        this.verticalSpace = verticalSpace;
    }
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = verticalSpace;

    }
}

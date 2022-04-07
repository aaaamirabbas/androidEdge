package io.github.aaaamirabbas.edge.utils.live_data;


import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public class VolatileLiveData<T> extends MutableLiveData<T> {


    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @MainThread
    public void observe(@NotNull LifecycleOwner owner, @NotNull final Observer<? super T> observer) {
        mPending.set(false);
        super.observe(owner, t -> {
            if (mPending.get()) {
                observer.onChanged(t);
            }
        });
    }

    @MainThread
    public void setValue(@Nullable T t) {
        mPending.set(true);
        super.setValue(t);
    }
}
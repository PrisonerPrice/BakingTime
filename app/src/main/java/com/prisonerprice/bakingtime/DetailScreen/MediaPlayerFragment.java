package com.prisonerprice.bakingtime.DetailScreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.prisonerprice.bakingtime.Model.Step;
import com.prisonerprice.bakingtime.R;

public class MediaPlayerFragment extends Fragment {

    private static final String TAG = MediaPlayerFragment.class.getSimpleName();
    private static final String POSITION_KEY = "position_key";

    private PlayerView playerView;
    private LinearLayout noVideoIndicator;
    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    private String res;
    private boolean hasVideo = false;

    private DetailViewModel model = DetailViewModel.getInstance();

    public MediaPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView");

        View rootView = inflater.inflate(R.layout.fragment_media_player, container, false);

        model.getSelectedStep().observe(getViewLifecycleOwner(), step -> {
            if (step != null) {
                playbackPosition = model.readCache(step);
                if (step.getVideoUrl() != null && step.getVideoUrl().length() > 0) {
                    res = step.getVideoUrl();
                    hasVideo = true;
                    changeMediaSource(buildMediaSource(Uri.parse(res)));
                } else if (step.getThumbnailUrl() != null && step.getThumbnailUrl().length() > 0) {
                    res = step.getThumbnailUrl();
                    hasVideo = true;
                    changeMediaSource(buildMediaSource(Uri.parse(res)));
                } else {
                    res = null;
                    hasVideo = false;
                }
            } else {
                hasVideo = false;
            }
            if (hasVideo) {
                playerView.setVisibility(View.VISIBLE);
                noVideoIndicator.setVisibility(View.INVISIBLE);
            } else {
                playerView.setVisibility(View.GONE);
                noVideoIndicator.setVisibility(View.VISIBLE);
            }
        });
        playerView = rootView.findViewById(R.id.exo_player);
        noVideoIndicator = rootView.findViewById(R.id.player_no_video_indicator);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23 && hasVideo) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
            if (hasVideo) initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void initializePlayer() {
        Log.d(TAG, "initializePlayer");
        player = new SimpleExoPlayer.Builder(getContext()).build();
        playerView.setPlayer(player);
        if (res != null && res.length() > 0) {
            Uri uri = Uri.parse(res);
            MediaSource mediaSource = buildMediaSource(uri);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);
            player.prepare(mediaSource, false, false);
        }
    }

    private void changeMediaSource(MediaSource mediaSource) {
        Log.d(TAG, "changerMediaSource");
        if (player != null && mediaSource != null) {
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);
            player.prepare(mediaSource, false, false);
        }
    }

    private void releasePlayer() {
        if (player != null) {
            Log.d(TAG, "The player is released");
            //playbackPosition = player.getCurrentPosition();
            model.writeCache(model.getSelectedStep().getValue(), player.getCurrentPosition());
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(), "MediaPlayerFragment");
        ProgressiveMediaSource.Factory mediaSourceFactory =
                new ProgressiveMediaSource.Factory(dataSourceFactory);

        // Create a media source using the supplied URI
        MediaSource mediaSource = mediaSourceFactory.createMediaSource(uri);

        return mediaSource;
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}

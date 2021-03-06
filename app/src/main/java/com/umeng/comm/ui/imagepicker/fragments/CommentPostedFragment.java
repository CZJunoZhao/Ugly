/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 Umeng, Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.umeng.comm.ui.imagepicker.fragments;

import com.umeng.comm.ui.imagepicker.adapters.ReceivedCommentAdapter;
import com.umeng.comm.ui.imagepicker.presenter.impl.CommentPostedPresenter;

/**
 * 我的消息页面中的当前用户发布的评论的列表
 */
public class CommentPostedFragment extends CommentReceivedFragment {

    @Override
    protected CommentPostedPresenter createPresenters() {
        return new CommentPostedPresenter(this);
    }

    protected ReceivedCommentAdapter createListViewAdapter() {
        ReceivedCommentAdapter adapter = new ReceivedCommentAdapter(getActivity(), false);
        adapter.setUserInfoClassName(mUserInfoClass);
        adapter.setTopicDetailClassName(mTopicDetailClassName);
        adapter.setFeedDetailClassName(mFeedDetailClassName);
        return adapter;
    }
}

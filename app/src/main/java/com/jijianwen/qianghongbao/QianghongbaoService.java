package com.jijianwen.qianghongbao;

import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class QianghongbaoService extends AccessibilityService {
    private AccessibilityNodeInfo aRootNodeInfo = null;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        aRootNodeInfo = event.getSource();
        if (aRootNodeInfo == null) {
            return;
        }

        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            List<AccessibilityNodeInfo> hongbaoList = aRootNodeInfo.findAccessibilityNodeInfosByText("微信红包");
            if (hongbaoList.size() > 0) {
                AccessibilityNodeInfo curNodeInfo = hongbaoList.get(hongbaoList.size() - 1);
                curNodeInfo.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }

        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            List<AccessibilityNodeInfo> clickedWindowlist = aRootNodeInfo.findAccessibilityNodeInfosByText("拆红包");
            if (clickedWindowlist.size() > 0) {
                AccessibilityNodeInfo curNodeInfo = clickedWindowlist.get(clickedWindowlist.size() -1);
                curNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
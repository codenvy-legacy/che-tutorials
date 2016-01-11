/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.ide.tutorial.notification.action;

import org.eclipse.che.ide.api.notification.NotificationListener;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.notification.StatusNotification;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;


/**
 * The action for showing PROGRESS notification.
 *
 * @author Andrey Plotnikov
 * @author Vlad Zhukovskyi
 */
public class ShowProgressNotification extends Action implements NotificationListener {
    private NotificationManager notificationManager;
    private StatusNotification  notification;
    private Timer timer = new Timer() {
        @Override
        public void run() {
            boolean isSuccessful = Window.confirm("Close notification as successful? Otherwise it will be failed.");
            if (isSuccessful) {
                notification.setStatus(StatusNotification.Status.SUCCESS);
                notification.setContent("I've finished progress...");
            } else {
                notification.setStatus(StatusNotification.Status.FAIL);
                notification.setContent("Some error is happened...");
            }
            notification = null;
        }
    };

    @Inject
    public ShowProgressNotification(NotificationManager notificationManager) {
        super("Show progress notification", "This action shows progress notification");
        this.notificationManager = notificationManager;
    }

    /** {@inheritDoc} */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (notification == null) {
            notification =
                    new StatusNotification("Status notification", "I'm doing something...", StatusNotification.Status.PROGRESS, true, null,
                                           this);
            notificationManager.notify(notification);
            timer.schedule(10000);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onClick() {
        //stub
    }

    /** {@inheritDoc} */
    @Override
    public void onDoubleClick() {
        Window.alert("You've opened notification!");
    }

    /** {@inheritDoc} */
    @Override
    public void onClose() {
        timer.cancel();
        notification.setStatus(StatusNotification.Status.SUCCESS);
        notification.setContent("The process was stopped...");
        notification = null;
    }
}
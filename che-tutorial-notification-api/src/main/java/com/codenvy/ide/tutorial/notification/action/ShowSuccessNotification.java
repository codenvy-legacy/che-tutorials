/*******************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.ide.tutorial.notification.action;

import org.eclipse.che.ide.api.notification.Notification;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.notification.StatusNotification;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The action for showing INFO notification.
 *
 * @author Andrey Plotnikov
 * @author Vlad Zhukovskyi
 */
@Singleton
public class ShowSuccessNotification extends Action {
    private NotificationManager notificationManager;

    @Inject
    public ShowSuccessNotification(NotificationManager notificationManager) {
        super("Show SUCCESS notification", "This action shows SUCCESS notification");
        this.notificationManager = notificationManager;
    }

    /** {@inheritDoc} */
    @Override
    public void actionPerformed(ActionEvent e) {
        Notification notification = new StatusNotification("This is a info notification...", StatusNotification.Status.SUCCESS, true);
        notificationManager.notify(notification);
    }
}
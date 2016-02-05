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
package com.codenvy.ide.ext.helloworld.client.action;

/**
 * As usual, importing resources, related to Action API.
 * The 3rd import is required to call a default alert box.
 */

import com.codenvy.ide.ext.helloworld.client.Resource;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;

import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.rest.AsyncRequestCallback;
import org.eclipse.che.ide.rest.AsyncRequestFactory;
import org.eclipse.che.ide.rest.StringUnmarshaller;

public class HelloWorldAction extends Action {
    /**
     * Define a constructor and pass over text to be displayed in the dialogue box.
     */
    private final static Resource resource = GWT.create(Resource.class);

    private final AsyncRequestFactory asyncRequestFactory;

    @Inject
    public HelloWorldAction(AsyncRequestFactory asyncRequestFactory) {
        super(null, null, resource.hello(), null);
        this.asyncRequestFactory = asyncRequestFactory;
    }

    /**
     * Getting previously registered server side component and adding  text input to it (asking to enter name). To get a server side
     * component a path is provided which is /api/ComponentName.
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        final String name = Window.prompt("What's your name?", "");
        asyncRequestFactory.createGetRequest("/api/hello/" + name).send(new AsyncRequestCallback<String>(new StringUnmarshaller()) {
            @Override
            protected void onSuccess(String answer) {
                Window.alert(answer);
            }

            @Override
            protected void onFailure(Throwable arg0) {
            }
        });
    }
}

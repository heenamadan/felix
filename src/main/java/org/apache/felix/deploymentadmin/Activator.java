/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.felix.deploymentadmin;

import org.apache.felix.dependencymanager.DependencyActivatorBase;
import org.apache.felix.dependencymanager.DependencyManager;
import org.osgi.framework.BundleContext;
import org.osgi.service.deploymentadmin.DeploymentAdmin;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.log.LogService;
import org.osgi.service.packageadmin.PackageAdmin;

/**
 * Bundle activator for the deployment admin bundle
 *
 * @author  Christian van Spaandonk
 * @version $Revision: 8712 $
 */
public class Activator extends DependencyActivatorBase {

    public void init(BundleContext context, DependencyManager manager) throws Exception {
        manager.add(createService()
            .setInterface(DeploymentAdmin.class.getName(), null)
            .setImplementation(DeploymentAdminImpl.class)
            .add(createServiceDependency()
                .setService(PackageAdmin.class)
                .setRequired(true))
            .add(createServiceDependency()
                .setService(EventAdmin.class)
                .setRequired(false))
            .add(createServiceDependency()
                .setService(LogService.class)
                .setRequired(false)));
    }

    public void destroy(BundleContext context, DependencyManager manager) throws Exception {
        // do nothing
    }
}
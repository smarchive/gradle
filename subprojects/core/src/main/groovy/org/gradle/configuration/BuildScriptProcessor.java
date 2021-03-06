/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.configuration;

import org.gradle.api.internal.project.ProjectInternal;
import org.gradle.api.internal.project.ProjectStateInternal;
import org.gradle.util.Clock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuildScriptProcessor implements ProjectEvaluator {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuildScriptProcessor.class);
    private final ScriptPluginFactory configurerFactory;

    public BuildScriptProcessor(ScriptPluginFactory configurerFactory) {
        this.configurerFactory = configurerFactory;
    }

    public void evaluate(ProjectInternal project, ProjectStateInternal state) {
        LOGGER.info(String.format("Evaluating %s using %s.", project, project.getBuildScriptSource().getDisplayName()));
        Clock clock = new Clock();

        try {
            ScriptPlugin configurer = configurerFactory.create(project.getBuildScriptSource());
            configurer.apply(project);
        } catch (Exception e) {
            state.executed(e);
        }

        LOGGER.debug("Timing: Running the build script took " + clock.getTime());
    }
}

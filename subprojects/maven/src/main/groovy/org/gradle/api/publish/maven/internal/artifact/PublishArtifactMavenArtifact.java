/*
 * Copyright 2012 the original author or authors.
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
package org.gradle.api.publish.maven.internal.artifact;

import org.gradle.api.Buildable;
import org.gradle.api.artifacts.PublishArtifact;
import org.gradle.api.publish.maven.MavenArtifact;
import org.gradle.api.tasks.TaskDependency;

import java.io.File;

public class PublishArtifactMavenArtifact extends ConfigurableMavenArtifact implements MavenArtifact, Buildable {
    private final PublishArtifact delegate;

    public PublishArtifactMavenArtifact(PublishArtifact delegate) {
        this.delegate = delegate;
    }

    public String getBaseExtension() {
        return delegate.getExtension();
    }

    public String getBaseClassifier() {
        return delegate.getClassifier();
    }

    public File getFile() {
        return delegate.getFile();
    }

    public TaskDependency getBuildDependencies() {
        return delegate.getBuildDependencies();
    }
}

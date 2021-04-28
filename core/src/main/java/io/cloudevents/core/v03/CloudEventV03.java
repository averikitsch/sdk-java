/*
 * Copyright 2018-Present The CloudEvents Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.cloudevents.core.v03;

import io.cloudevents.CloudEventData;
import io.cloudevents.SpecVersion;
import io.cloudevents.core.impl.BaseCloudEvent;
import io.cloudevents.lang.Nullable;
import io.cloudevents.rw.CloudEventContextWriter;
import io.cloudevents.rw.CloudEventRWException;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * CloudEvent implementation for v0.3
 *
 * @author fabiojose
 * @author slinkydeveloper
 */
public final class CloudEventV03 extends BaseCloudEvent {

    /**
     * The name of the <a href="https://github.com/cloudevents/spec/blob/v0.3/spec.md#id">id</a> attribute
     */
    public final static String ID = "id";

    /**
     * The name of the <a href="https://github.com/cloudevents/spec/blob/v0.3/spec.md#source">source</a> attribute
     */
    public final static String SOURCE = "source";

    /**
     * The name of the <a href="https://github.com/cloudevents/spec/blob/v0.3/spec.md#specversion">specversion</a> attribute
     */
    public final static String SPECVERSION = "specversion";

    /**
     * The name of the <a href="https://github.com/cloudevents/spec/blob/v0.3/spec.md#type">type</a> attribute
     */
    public final static String TYPE = "type";

    /**
     * The name of the <a href="https://github.com/cloudevents/spec/blob/v0.3/spec.md#time">time</a> attribute
     */
    public final static String TIME = "time";

    /**
     * The name of the <a href="https://github.com/cloudevents/spec/blob/v0.3/spec.md#schemaurl">schemaurl</a> attribute
     */
    public final static String SCHEMAURL = "schemaurl";

    /**
     * The name of the <a href="https://github.com/cloudevents/spec/blob/v0.3/spec.md#datacontenttype">datacontenttype</a> attribute
     */
    public final static String DATACONTENTTYPE = "datacontenttype";

    /**
     * The name of the <a href="https://github.com/cloudevents/spec/blob/v0.3/spec.md#datacontentencoding">datacontentencoding</a> attribute
     */
    public final static String DATACONTENTENCODING = "datacontentencoding";

    /**
     * The name of the <a href="https://github.com/cloudevents/spec/blob/v0.3/spec.md#subject">subject</a> attribute
     */
    public final static String SUBJECT = "subject";

    private final String id;
    private final URI source;
    private final String type;
    private final String datacontenttype;
    private final URI schemaurl;
    private final String subject;
    private final OffsetDateTime time;

    public CloudEventV03(String id, URI source, String type,
                         OffsetDateTime time, URI schemaurl,
                         String datacontenttype, String subject,
                         CloudEventData data, Map<String, Object> extensions) {
        super(data, extensions);

        this.id = id;
        this.source = source;
        this.type = type;

        this.time = time;
        this.schemaurl = schemaurl;
        this.datacontenttype = datacontenttype;
        this.subject = subject;
    }

    public SpecVersion getSpecVersion() {
        return SpecVersion.V03;
    }

    public String getId() {
        return id;
    }

    public URI getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public String getDataContentType() {
        return datacontenttype;
    }

    public URI getDataSchema() {
        return schemaurl;
    }

    @Nullable
    public URI getSchemaUrl() {
        return schemaurl;
    }

    public String getSubject() {
        return subject;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    @Override
    public Object getAttribute(String attributeName) {
        switch (attributeName) {
            case SPECVERSION:
                return getSpecVersion();
            case ID:
                return this.id;
            case SOURCE:
                return this.source;
            case TYPE:
                return this.type;
            case DATACONTENTTYPE:
                return this.datacontenttype;
            case SCHEMAURL:
                return this.schemaurl;
            case SUBJECT:
                return this.subject;
            case TIME:
                return this.time;
            case DATACONTENTENCODING:
                // We don't save datacontentencoding, but the attribute name is valid, hence we just return always null
                return null;
        }
        throw new IllegalArgumentException("Spec version v0.3 doesn't have attribute named " + attributeName);
    }

    @Override
    public void readContext(CloudEventContextWriter writer) throws CloudEventRWException {
        writer.withContextAttribute(
            ID,
            this.id
        );
        writer.withContextAttribute(
            SOURCE,
            this.source
        );
        writer.withContextAttribute(
            TYPE,
            this.type
        );
        if (this.datacontenttype != null) {
            writer.withContextAttribute(
                DATACONTENTTYPE,
                this.datacontenttype
            );
        }
        if (this.schemaurl != null) {
            writer.withContextAttribute(
                SCHEMAURL,
                this.schemaurl
            );
        }
        if (this.subject != null) {
            writer.withContextAttribute(
                SUBJECT,
                this.subject
            );
        }
        if (this.time != null) {
            writer.withContextAttribute(
                TIME,
                this.time
            );
        }
        this.readExtensions(writer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CloudEventV03 that = (CloudEventV03) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getSource(), that.getSource()) &&
            Objects.equals(getType(), that.getType()) &&
            Objects.equals(datacontenttype, that.datacontenttype) &&
            Objects.equals(schemaurl, that.schemaurl) &&
            Objects.equals(getSubject(), that.getSubject()) &&
            Objects.equals(getTime(), that.getTime()) &&
            Objects.equals(getData(), that.getData()) &&
            Objects.equals(this.extensions, that.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSource(), getType(), datacontenttype, schemaurl, getSubject(), getTime(), getData(), this.extensions);
    }

    @Override
    public String toString() {
        return "CloudEvent{" +
            "id='" + id + '\'' +
            ", source=" + source +
            ", type='" + type + '\'' +
            ((datacontenttype != null) ? ", datacontenttype='" + datacontenttype + '\'' : "") +
            ((schemaurl != null) ? ", schemaurl=" + schemaurl : "") +
            ((subject != null) ? ", subject='" + subject + '\'' : "") +
            ((time != null) ? ", time=" + time : "") +
            ((getData() != null) ? ", data=" + getData() : "") +
            ", extensions=" + this.extensions +
            '}';
    }
}

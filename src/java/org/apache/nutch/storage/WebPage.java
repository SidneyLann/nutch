/**
 *Licensed to the Apache Software Foundation (ASF) under one
 *or more contributor license agreements.  See the NOTICE file
 *distributed with this work for additional information
 *regarding copyright ownership.  The ASF licenses this file
 *to you under the Apache License, Version 2.0 (the"
 *License"); you may not use this file except in compliance
 *with the License.  You may obtain a copy of the License at
 *
  * http://www.apache.org/licenses/LICENSE-2.0
 * 
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 */
package org.apache.nutch.storage;  

/** WebPage is the primary data structure in Nutch representing crawl data for a given WebPage at some point in time */
public class WebPage extends org.apache.gora.persistency.impl.PersistentBase implements org.apache.avro.specific.SpecificRecord, org.apache.gora.persistency.Persistent {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"WebPage\",\"namespace\":\"org.apache.nutch.storage\",\"doc\":\"WebPage is the primary data structure in Nutch representing crawl data for a given WebPage at some point in time\",\"fields\":[{\"name\":\"baseUrl\",\"type\":[\"null\",\"string\"],\"doc\":\"The original associated with this WebPage.\",\"default\":null},{\"name\":\"status\",\"type\":\"int\",\"doc\":\"A crawl status associated with the WebPage, can be of value STATUS_UNFETCHED - WebPage was not fetched yet, STATUS_FETCHED - WebPage was successfully fetched, STATUS_GONE - WebPage no longer exists, STATUS_REDIR_TEMP - WebPage temporarily redirects to other page, STATUS_REDIR_PERM - WebPage permanently redirects to other page, STATUS_RETRY - Fetching unsuccessful, needs to be retried e.g. transient errors and STATUS_NOTMODIFIED - fetching successful - page is not modified\",\"default\":0},{\"name\":\"fetchTime\",\"type\":\"long\",\"doc\":\"The system time in milliseconds for when the page was fetched.\",\"default\":0},{\"name\":\"prevFetchTime\",\"type\":\"long\",\"doc\":\"The system time in milliseconds for when the page was last fetched if it was previously fetched which can be used to calculate time delta within a fetching schedule implementation\",\"default\":0},{\"name\":\"fetchInterval\",\"type\":\"int\",\"doc\":\"The default number of seconds between re-fetches of a page. The default is considered as 30 days unless a custom fetch schedle is implemented.\",\"default\":0},{\"name\":\"retriesSinceFetch\",\"type\":\"int\",\"doc\":\"The number of retried attempts at fetching the WebPage since it was last successfully fetched.\",\"default\":0},{\"name\":\"modifiedTime\",\"type\":\"long\",\"doc\":\"The system time in milliseconds for when this WebPage was modified by the WebPage author, if this is not available we default to the server for this information. This is important to understand the changing nature of the WebPage.\",\"default\":0},{\"name\":\"prevModifiedTime\",\"type\":\"long\",\"doc\":\"The system time in milliseconds for when this WebPage was previously modified by the author, if this is not available then we default to the server for this information. This is important to understand the changing nature of a WebPage.\",\"default\":0},{\"name\":\"protocolStatus\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"ProtocolStatus\",\"doc\":\"A nested container representing data captured from web server responses.\",\"fields\":[{\"name\":\"code\",\"type\":\"int\",\"doc\":\"A protocol response code which can be one of SUCCESS - content was retrieved without errors, FAILED - Content was not retrieved. Any further errors may be indicated in args, PROTO_NOT_FOUND - This protocol was not found. Application may attempt to retry later, GONE - Resource is gone, MOVED - Resource has moved permanently. New url should be found in args, TEMP_MOVED - Resource has moved temporarily. New url should be found in args., NOTFOUND - Resource was not found, RETRY - Temporary failure. Application may retry immediately., EXCEPTION - Unspecified exception occured. Further information may be provided in args., ACCESS_DENIED - Access denied - authorization required, but missing/incorrect., ROBOTS_DENIED - Access denied by robots.txt rules., REDIR_EXCEEDED - Too many redirects., NOTFETCHING - Not fetching., NOTMODIFIED - Unchanged since the last fetch., WOULDBLOCK - Request was refused by protocol plugins, because it would block. The expected number of milliseconds to wait before retry may be provided in args., BLOCKED - Thread was blocked http.max.delays times during fetching.\",\"default\":0},{\"name\":\"args\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"doc\":\"Optional arguments supplied to compliment and/or justify the response code.\",\"default\":[]},{\"name\":\"lastModified\",\"type\":\"long\",\"doc\":\"A server reponse indicating when this page was last modified, this can be unreliable at times hence this is used as a default fall back value for the preferred 'modifiedTime' and 'preModifiedTime' obtained from the WebPage itself.\",\"default\":0}]}],\"default\":null},{\"name\":\"content\",\"type\":[\"null\",\"bytes\"],\"doc\":\"The entire raw document content e.g. raw XHTML\",\"default\":null},{\"name\":\"contentType\",\"type\":[\"null\",\"string\"],\"doc\":\"The type of the content contained within the document itself. ContentType is an alias for MimeType. Historically, this parameter was only called MimeType, but since this is actually the value included in the HTTP Content-Type header, it can also include the character set encoding, which makes it more than just a MimeType specification. If MimeType is specified e.g. not None, that value is used. Otherwise, ContentType is used. If neither is given, the DEFAULT_CONTENT_TYPE setting is used.\",\"default\":null},{\"name\":\"prevSignature\",\"type\":[\"null\",\"bytes\"],\"doc\":\"An implementation of a WebPage's previous signature from which it can be identified and referenced at any point in time. This can be used to uniquely identify WebPage deltas based on page fingerprints.\",\"default\":null},{\"name\":\"signature\",\"type\":[\"null\",\"bytes\"],\"doc\":\"An implementation of a WebPage's signature from which it can be identified and referenced at any point in time. This is essentially the WebPage's fingerprint represnting its state for any point in time.\",\"default\":null},{\"name\":\"title\",\"type\":[\"null\",\"string\"],\"doc\":\"The title of the WebPage.\",\"default\":null},{\"name\":\"text\",\"type\":[\"null\",\"string\"],\"doc\":\"The textual content of the WebPage devoid from native markup.\",\"default\":null},{\"name\":\"parseStatus\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"ParseStatus\",\"doc\":\"A nested container representing parse status data captured from invocation of parsers on fetch of a WebPage\",\"fields\":[{\"name\":\"majorCode\",\"type\":\"int\",\"doc\":\"Major parsing status' including NOTPARSED (Parsing was not performed), SUCCESS (Parsing succeeded), FAILED (General failure. There may be a more specific error message in arguments.)\",\"default\":0},{\"name\":\"minorCode\",\"type\":\"int\",\"doc\":\"Minor parsing status' including SUCCESS_OK - Successful parse devoid of anomalies or issues, SUCCESS_REDIRECT - Parsed content contains a directive to redirect to another URL. The target URL can be retrieved from the arguments., FAILED_EXCEPTION - Parsing failed. An Exception occured which may be retrieved from the arguments., FAILED_TRUNCATED - Parsing failed. Content was truncated, but the parser cannot handle incomplete content., FAILED_INVALID_FORMAT - Parsing failed. Invalid format e.g. the content may be corrupted or of wrong type., FAILED_MISSING_PARTS - Parsing failed. Other related parts of the content are needed to complete parsing. The list of URLs to missing parts may be provided in arguments. The Fetcher may decide to fetch these parts at once, then put them into Content.metadata, and supply them for re-parsing., FAILED_MISING_CONTENT - Parsing failed. There was no content to be parsed - probably caused by errors at protocol stage.\",\"default\":0},{\"name\":\"args\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"doc\":\"Optional arguments supplied to compliment and/or justify the parse status code.\",\"default\":[]}]}],\"default\":null},{\"name\":\"score\",\"type\":\"float\",\"doc\":\"A score used to determine a WebPage's relevance within the web graph it is part of. This score may change over time based on graph characteristics.\",\"default\":0},{\"name\":\"reprUrl\",\"type\":[\"null\",\"string\"],\"doc\":\"In the case where we are given two urls, a source and a destination of a redirect, we should determine and persist the representative url. The logic used to determine this is based largely on Yahoo!'s Slurp Crawler\",\"default\":null},{\"name\":\"headers\",\"type\":{\"type\":\"map\",\"values\":[\"null\",\"string\"]},\"doc\":\"Header information returned from the web server used to server the content which is subsequently fetched from. This includes keys such as TRANSFER_ENCODING, CONTENT_ENCODING, CONTENT_LANGUAGE, CONTENT_LENGTH, CONTENT_LOCATION, CONTENT_DISPOSITION, CONTENT_MD5, CONTENT_TYPE, LAST_MODIFIED and LOCATION.\",\"default\":{}},{\"name\":\"outlinks\",\"type\":{\"type\":\"map\",\"values\":[\"null\",\"string\"]},\"doc\":\"Embedded hyperlinks which direct outside of the current domain.\",\"default\":{}},{\"name\":\"inlinks\",\"type\":{\"type\":\"map\",\"values\":[\"null\",\"string\"]},\"doc\":\"Embedded hyperlinks which link to pages within the current domain.\",\"default\":{}},{\"name\":\"markers\",\"type\":{\"type\":\"map\",\"values\":[\"null\",\"string\"]},\"doc\":\"Markers flags which represent user and machine decisions which have affected influenced a WebPage's current state. Markers can be system specific and user machine driven in nature. They are assigned to a WebPage on a job-by-job basis and thier values indicative of what actions should be associated with a WebPage.\",\"default\":{}},{\"name\":\"metadata\",\"type\":{\"type\":\"map\",\"values\":[\"null\",\"bytes\"]},\"doc\":\"A multi-valued metadata container used for storing everything from structured WebPage characterists, to ad-hoc extraction and metadata augmentation for any given WebPage.\",\"default\":{}},{\"name\":\"batchId\",\"type\":[\"null\",\"string\"],\"doc\":\"A batchId that this WebPage is assigned to. WebPage's are fetched in batches, called fetchlists. Pages are partitioned but can always be associated and fetched alongside pages of similar value (within a crawl cycle) based on batchId.\",\"default\":null},{\"name\":\"sitemaps\",\"type\":{\"type\":\"map\",\"values\":[\"null\",\"string\"]},\"doc\":\"Sitemap urls in robot.txt\",\"default\":{}},{\"name\":\"stmPriority\",\"type\":\"float\",\"doc\":\"\",\"default\":0}]}");
  private static final long serialVersionUID = 3425250034311490142L;
  /** Enum containing all data bean's fields. */
  public static enum Field {
    BASE_URL(0, "baseUrl"),
    STATUS(1, "status"),
    FETCH_TIME(2, "fetchTime"),
    PREV_FETCH_TIME(3, "prevFetchTime"),
    FETCH_INTERVAL(4, "fetchInterval"),
    RETRIES_SINCE_FETCH(5, "retriesSinceFetch"),
    MODIFIED_TIME(6, "modifiedTime"),
    PREV_MODIFIED_TIME(7, "prevModifiedTime"),
    PROTOCOL_STATUS(8, "protocolStatus"),
    CONTENT(9, "content"),
    CONTENT_TYPE(10, "contentType"),
    PREV_SIGNATURE(11, "prevSignature"),
    SIGNATURE(12, "signature"),
    TITLE(13, "title"),
    TEXT(14, "text"),
    PARSE_STATUS(15, "parseStatus"),
    SCORE(16, "score"),
    REPR_URL(17, "reprUrl"),
    HEADERS(18, "headers"),
    OUTLINKS(19, "outlinks"),
    INLINKS(20, "inlinks"),
    MARKERS(21, "markers"),
    METADATA(22, "metadata"),
    BATCH_ID(23, "batchId"),
    SITEMAPS(24, "sitemaps"),
    STM_PRIORITY(25, "stmPriority"),
    ;
    /**
     * Field's index.
     */
    private int index;

    /**
     * Field's name.
     */
    private String name;

    /**
     * Field's constructor
     * @param index field's index.
     * @param name field's name.
     */
    Field(int index, String name) {this.index=index;this.name=name;}

    /**
     * Gets field's index.
     * @return int field's index.
     */
    public int getIndex() {return index;}

    /**
     * Gets field's name.
     * @return String field's name.
     */
    public String getName() {return name;}

    /**
     * Gets field's attributes to string.
     * @return String field's attributes to string.
     */
    public String toString() {return name;}
  };

  public static final String[] _ALL_FIELDS = {
  "baseUrl",
  "status",
  "fetchTime",
  "prevFetchTime",
  "fetchInterval",
  "retriesSinceFetch",
  "modifiedTime",
  "prevModifiedTime",
  "protocolStatus",
  "content",
  "contentType",
  "prevSignature",
  "signature",
  "title",
  "text",
  "parseStatus",
  "score",
  "reprUrl",
  "headers",
  "outlinks",
  "inlinks",
  "markers",
  "metadata",
  "batchId",
  "sitemaps",
  "stmPriority",
  };

  /**
   * Gets the total field count.
   * @return int field count
   */
  public int getFieldsCount() {
    return WebPage._ALL_FIELDS.length;
  }

  /** The original associated with this WebPage. */
  private java.lang.CharSequence baseUrl;
  /** A crawl status associated with the WebPage, can be of value STATUS_UNFETCHED - WebPage was not fetched yet, STATUS_FETCHED - WebPage was successfully fetched, STATUS_GONE - WebPage no longer exists, STATUS_REDIR_TEMP - WebPage temporarily redirects to other page, STATUS_REDIR_PERM - WebPage permanently redirects to other page, STATUS_RETRY - Fetching unsuccessful, needs to be retried e.g. transient errors and STATUS_NOTMODIFIED - fetching successful - page is not modified */
  private int status;
  /** The system time in milliseconds for when the page was fetched. */
  private long fetchTime;
  /** The system time in milliseconds for when the page was last fetched if it was previously fetched which can be used to calculate time delta within a fetching schedule implementation */
  private long prevFetchTime;
  /** The default number of seconds between re-fetches of a page. The default is considered as 30 days unless a custom fetch schedle is implemented. */
  private int fetchInterval;
  /** The number of retried attempts at fetching the WebPage since it was last successfully fetched. */
  private int retriesSinceFetch;
  /** The system time in milliseconds for when this WebPage was modified by the WebPage author, if this is not available we default to the server for this information. This is important to understand the changing nature of the WebPage. */
  private long modifiedTime;
  /** The system time in milliseconds for when this WebPage was previously modified by the author, if this is not available then we default to the server for this information. This is important to understand the changing nature of a WebPage. */
  private long prevModifiedTime;
  private org.apache.nutch.storage.ProtocolStatus protocolStatus;
  /** The entire raw document content e.g. raw XHTML */
  private java.nio.ByteBuffer content;
  /** The type of the content contained within the document itself. ContentType is an alias for MimeType. Historically, this parameter was only called MimeType, but since this is actually the value included in the HTTP Content-Type header, it can also include the character set encoding, which makes it more than just a MimeType specification. If MimeType is specified e.g. not None, that value is used. Otherwise, ContentType is used. If neither is given, the DEFAULT_CONTENT_TYPE setting is used. */
  private java.lang.CharSequence contentType;
  /** An implementation of a WebPage's previous signature from which it can be identified and referenced at any point in time. This can be used to uniquely identify WebPage deltas based on page fingerprints. */
  private java.nio.ByteBuffer prevSignature;
  /** An implementation of a WebPage's signature from which it can be identified and referenced at any point in time. This is essentially the WebPage's fingerprint represnting its state for any point in time. */
  private java.nio.ByteBuffer signature;
  /** The title of the WebPage. */
  private java.lang.CharSequence title;
  /** The textual content of the WebPage devoid from native markup. */
  private java.lang.CharSequence text;
  private org.apache.nutch.storage.ParseStatus parseStatus;
  /** A score used to determine a WebPage's relevance within the web graph it is part of. This score may change over time based on graph characteristics. */
  private float score;
  /** In the case where we are given two urls, a source and a destination of a redirect, we should determine and persist the representative url. The logic used to determine this is based largely on Yahoo!'s Slurp Crawler */
  private java.lang.CharSequence reprUrl;
  /** Header information returned from the web server used to server the content which is subsequently fetched from. This includes keys such as TRANSFER_ENCODING, CONTENT_ENCODING, CONTENT_LANGUAGE, CONTENT_LENGTH, CONTENT_LOCATION, CONTENT_DISPOSITION, CONTENT_MD5, CONTENT_TYPE, LAST_MODIFIED and LOCATION. */
  private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> headers;
  /** Embedded hyperlinks which direct outside of the current domain. */
  private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> outlinks;
  /** Embedded hyperlinks which link to pages within the current domain. */
  private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> inlinks;
  /** Markers flags which represent user and machine decisions which have affected influenced a WebPage's current state. Markers can be system specific and user machine driven in nature. They are assigned to a WebPage on a job-by-job basis and thier values indicative of what actions should be associated with a WebPage. */
  private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> markers;
  /** A multi-valued metadata container used for storing everything from structured WebPage characterists, to ad-hoc extraction and metadata augmentation for any given WebPage. */
  private java.util.Map<java.lang.CharSequence,java.nio.ByteBuffer> metadata;
  /** A batchId that this WebPage is assigned to. WebPage's are fetched in batches, called fetchlists. Pages are partitioned but can always be associated and fetched alongside pages of similar value (within a crawl cycle) based on batchId. */
  private java.lang.CharSequence batchId;
  /** Sitemap urls in robot.txt */
  private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> sitemaps;
  /**  */
  private float stmPriority;
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return baseUrl;
    case 1: return status;
    case 2: return fetchTime;
    case 3: return prevFetchTime;
    case 4: return fetchInterval;
    case 5: return retriesSinceFetch;
    case 6: return modifiedTime;
    case 7: return prevModifiedTime;
    case 8: return protocolStatus;
    case 9: return content;
    case 10: return contentType;
    case 11: return prevSignature;
    case 12: return signature;
    case 13: return title;
    case 14: return text;
    case 15: return parseStatus;
    case 16: return score;
    case 17: return reprUrl;
    case 18: return headers;
    case 19: return outlinks;
    case 20: return inlinks;
    case 21: return markers;
    case 22: return metadata;
    case 23: return batchId;
    case 24: return sitemaps;
    case 25: return stmPriority;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value) {
    switch (field$) {
    case 0: baseUrl = (java.lang.CharSequence)(value); break;
    case 1: status = (java.lang.Integer)(value); break;
    case 2: fetchTime = (java.lang.Long)(value); break;
    case 3: prevFetchTime = (java.lang.Long)(value); break;
    case 4: fetchInterval = (java.lang.Integer)(value); break;
    case 5: retriesSinceFetch = (java.lang.Integer)(value); break;
    case 6: modifiedTime = (java.lang.Long)(value); break;
    case 7: prevModifiedTime = (java.lang.Long)(value); break;
    case 8: protocolStatus = (org.apache.nutch.storage.ProtocolStatus)(value); break;
    case 9: content = (java.nio.ByteBuffer)(value); break;
    case 10: contentType = (java.lang.CharSequence)(value); break;
    case 11: prevSignature = (java.nio.ByteBuffer)(value); break;
    case 12: signature = (java.nio.ByteBuffer)(value); break;
    case 13: title = (java.lang.CharSequence)(value); break;
    case 14: text = (java.lang.CharSequence)(value); break;
    case 15: parseStatus = (org.apache.nutch.storage.ParseStatus)(value); break;
    case 16: score = (java.lang.Float)(value); break;
    case 17: reprUrl = (java.lang.CharSequence)(value); break;
    case 18: headers = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>)((value instanceof org.apache.gora.persistency.Dirtyable) ? value : new org.apache.gora.persistency.impl.DirtyMapWrapper((java.util.Map)value)); break;
    case 19: outlinks = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>)((value instanceof org.apache.gora.persistency.Dirtyable) ? value : new org.apache.gora.persistency.impl.DirtyMapWrapper((java.util.Map)value)); break;
    case 20: inlinks = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>)((value instanceof org.apache.gora.persistency.Dirtyable) ? value : new org.apache.gora.persistency.impl.DirtyMapWrapper((java.util.Map)value)); break;
    case 21: markers = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>)((value instanceof org.apache.gora.persistency.Dirtyable) ? value : new org.apache.gora.persistency.impl.DirtyMapWrapper((java.util.Map)value)); break;
    case 22: metadata = (java.util.Map<java.lang.CharSequence,java.nio.ByteBuffer>)((value instanceof org.apache.gora.persistency.Dirtyable) ? value : new org.apache.gora.persistency.impl.DirtyMapWrapper((java.util.Map)value)); break;
    case 23: batchId = (java.lang.CharSequence)(value); break;
    case 24: sitemaps = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>)((value instanceof org.apache.gora.persistency.Dirtyable) ? value : new org.apache.gora.persistency.impl.DirtyMapWrapper((java.util.Map)value)); break;
    case 25: stmPriority = (java.lang.Float)(value); break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'baseUrl' field.
   * The original associated with this WebPage.   */
  public java.lang.CharSequence getBaseUrl() {
    return baseUrl;
  }

  /**
   * Sets the value of the 'baseUrl' field.
   * The original associated with this WebPage.   * @param value the value to set.
   */
  public void setBaseUrl(java.lang.CharSequence value) {
    this.baseUrl = value;
    setDirty(0);
  }
  
  /**
   * Checks the dirty status of the 'baseUrl' field. A field is dirty if it represents a change that has not yet been written to the database.
   * The original associated with this WebPage.   * @param value the value to set.
   */
  public boolean isBaseUrlDirty() {
    return isDirty(0);
  }

  /**
   * Gets the value of the 'status' field.
   * A crawl status associated with the WebPage, can be of value STATUS_UNFETCHED - WebPage was not fetched yet, STATUS_FETCHED - WebPage was successfully fetched, STATUS_GONE - WebPage no longer exists, STATUS_REDIR_TEMP - WebPage temporarily redirects to other page, STATUS_REDIR_PERM - WebPage permanently redirects to other page, STATUS_RETRY - Fetching unsuccessful, needs to be retried e.g. transient errors and STATUS_NOTMODIFIED - fetching successful - page is not modified   */
  public java.lang.Integer getStatus() {
    return status;
  }

  /**
   * Sets the value of the 'status' field.
   * A crawl status associated with the WebPage, can be of value STATUS_UNFETCHED - WebPage was not fetched yet, STATUS_FETCHED - WebPage was successfully fetched, STATUS_GONE - WebPage no longer exists, STATUS_REDIR_TEMP - WebPage temporarily redirects to other page, STATUS_REDIR_PERM - WebPage permanently redirects to other page, STATUS_RETRY - Fetching unsuccessful, needs to be retried e.g. transient errors and STATUS_NOTMODIFIED - fetching successful - page is not modified   * @param value the value to set.
   */
  public void setStatus(java.lang.Integer value) {
    this.status = value;
    setDirty(1);
  }
  
  /**
   * Checks the dirty status of the 'status' field. A field is dirty if it represents a change that has not yet been written to the database.
   * A crawl status associated with the WebPage, can be of value STATUS_UNFETCHED - WebPage was not fetched yet, STATUS_FETCHED - WebPage was successfully fetched, STATUS_GONE - WebPage no longer exists, STATUS_REDIR_TEMP - WebPage temporarily redirects to other page, STATUS_REDIR_PERM - WebPage permanently redirects to other page, STATUS_RETRY - Fetching unsuccessful, needs to be retried e.g. transient errors and STATUS_NOTMODIFIED - fetching successful - page is not modified   * @param value the value to set.
   */
  public boolean isStatusDirty() {
    return isDirty(1);
  }

  /**
   * Gets the value of the 'fetchTime' field.
   * The system time in milliseconds for when the page was fetched.   */
  public java.lang.Long getFetchTime() {
    return fetchTime;
  }

  /**
   * Sets the value of the 'fetchTime' field.
   * The system time in milliseconds for when the page was fetched.   * @param value the value to set.
   */
  public void setFetchTime(java.lang.Long value) {
    this.fetchTime = value;
    setDirty(2);
  }
  
  /**
   * Checks the dirty status of the 'fetchTime' field. A field is dirty if it represents a change that has not yet been written to the database.
   * The system time in milliseconds for when the page was fetched.   * @param value the value to set.
   */
  public boolean isFetchTimeDirty() {
    return isDirty(2);
  }

  /**
   * Gets the value of the 'prevFetchTime' field.
   * The system time in milliseconds for when the page was last fetched if it was previously fetched which can be used to calculate time delta within a fetching schedule implementation   */
  public java.lang.Long getPrevFetchTime() {
    return prevFetchTime;
  }

  /**
   * Sets the value of the 'prevFetchTime' field.
   * The system time in milliseconds for when the page was last fetched if it was previously fetched which can be used to calculate time delta within a fetching schedule implementation   * @param value the value to set.
   */
  public void setPrevFetchTime(java.lang.Long value) {
    this.prevFetchTime = value;
    setDirty(3);
  }
  
  /**
   * Checks the dirty status of the 'prevFetchTime' field. A field is dirty if it represents a change that has not yet been written to the database.
   * The system time in milliseconds for when the page was last fetched if it was previously fetched which can be used to calculate time delta within a fetching schedule implementation   * @param value the value to set.
   */
  public boolean isPrevFetchTimeDirty() {
    return isDirty(3);
  }

  /**
   * Gets the value of the 'fetchInterval' field.
   * The default number of seconds between re-fetches of a page. The default is considered as 30 days unless a custom fetch schedle is implemented.   */
  public java.lang.Integer getFetchInterval() {
    return fetchInterval;
  }

  /**
   * Sets the value of the 'fetchInterval' field.
   * The default number of seconds between re-fetches of a page. The default is considered as 30 days unless a custom fetch schedle is implemented.   * @param value the value to set.
   */
  public void setFetchInterval(java.lang.Integer value) {
    this.fetchInterval = value;
    setDirty(4);
  }
  
  /**
   * Checks the dirty status of the 'fetchInterval' field. A field is dirty if it represents a change that has not yet been written to the database.
   * The default number of seconds between re-fetches of a page. The default is considered as 30 days unless a custom fetch schedle is implemented.   * @param value the value to set.
   */
  public boolean isFetchIntervalDirty() {
    return isDirty(4);
  }

  /**
   * Gets the value of the 'retriesSinceFetch' field.
   * The number of retried attempts at fetching the WebPage since it was last successfully fetched.   */
  public java.lang.Integer getRetriesSinceFetch() {
    return retriesSinceFetch;
  }

  /**
   * Sets the value of the 'retriesSinceFetch' field.
   * The number of retried attempts at fetching the WebPage since it was last successfully fetched.   * @param value the value to set.
   */
  public void setRetriesSinceFetch(java.lang.Integer value) {
    this.retriesSinceFetch = value;
    setDirty(5);
  }
  
  /**
   * Checks the dirty status of the 'retriesSinceFetch' field. A field is dirty if it represents a change that has not yet been written to the database.
   * The number of retried attempts at fetching the WebPage since it was last successfully fetched.   * @param value the value to set.
   */
  public boolean isRetriesSinceFetchDirty() {
    return isDirty(5);
  }

  /**
   * Gets the value of the 'modifiedTime' field.
   * The system time in milliseconds for when this WebPage was modified by the WebPage author, if this is not available we default to the server for this information. This is important to understand the changing nature of the WebPage.   */
  public java.lang.Long getModifiedTime() {
    return modifiedTime;
  }

  /**
   * Sets the value of the 'modifiedTime' field.
   * The system time in milliseconds for when this WebPage was modified by the WebPage author, if this is not available we default to the server for this information. This is important to understand the changing nature of the WebPage.   * @param value the value to set.
   */
  public void setModifiedTime(java.lang.Long value) {
    this.modifiedTime = value;
    setDirty(6);
  }
  
  /**
   * Checks the dirty status of the 'modifiedTime' field. A field is dirty if it represents a change that has not yet been written to the database.
   * The system time in milliseconds for when this WebPage was modified by the WebPage author, if this is not available we default to the server for this information. This is important to understand the changing nature of the WebPage.   * @param value the value to set.
   */
  public boolean isModifiedTimeDirty() {
    return isDirty(6);
  }

  /**
   * Gets the value of the 'prevModifiedTime' field.
   * The system time in milliseconds for when this WebPage was previously modified by the author, if this is not available then we default to the server for this information. This is important to understand the changing nature of a WebPage.   */
  public java.lang.Long getPrevModifiedTime() {
    return prevModifiedTime;
  }

  /**
   * Sets the value of the 'prevModifiedTime' field.
   * The system time in milliseconds for when this WebPage was previously modified by the author, if this is not available then we default to the server for this information. This is important to understand the changing nature of a WebPage.   * @param value the value to set.
   */
  public void setPrevModifiedTime(java.lang.Long value) {
    this.prevModifiedTime = value;
    setDirty(7);
  }
  
  /**
   * Checks the dirty status of the 'prevModifiedTime' field. A field is dirty if it represents a change that has not yet been written to the database.
   * The system time in milliseconds for when this WebPage was previously modified by the author, if this is not available then we default to the server for this information. This is important to understand the changing nature of a WebPage.   * @param value the value to set.
   */
  public boolean isPrevModifiedTimeDirty() {
    return isDirty(7);
  }

  /**
   * Gets the value of the 'protocolStatus' field.
   */
  public org.apache.nutch.storage.ProtocolStatus getProtocolStatus() {
    return protocolStatus;
  }

  /**
   * Sets the value of the 'protocolStatus' field.
   * @param value the value to set.
   */
  public void setProtocolStatus(org.apache.nutch.storage.ProtocolStatus value) {
    this.protocolStatus = value;
    setDirty(8);
  }
  
  /**
   * Checks the dirty status of the 'protocolStatus' field. A field is dirty if it represents a change that has not yet been written to the database.
   * @param value the value to set.
   */
  public boolean isProtocolStatusDirty() {
    return isDirty(8);
  }

  /**
   * Gets the value of the 'content' field.
   * The entire raw document content e.g. raw XHTML   */
  public java.nio.ByteBuffer getContent() {
    return content;
  }

  /**
   * Sets the value of the 'content' field.
   * The entire raw document content e.g. raw XHTML   * @param value the value to set.
   */
  public void setContent(java.nio.ByteBuffer value) {
    this.content = value;
    setDirty(9);
  }
  
  /**
   * Checks the dirty status of the 'content' field. A field is dirty if it represents a change that has not yet been written to the database.
   * The entire raw document content e.g. raw XHTML   * @param value the value to set.
   */
  public boolean isContentDirty() {
    return isDirty(9);
  }

  /**
   * Gets the value of the 'contentType' field.
   * The type of the content contained within the document itself. ContentType is an alias for MimeType. Historically, this parameter was only called MimeType, but since this is actually the value included in the HTTP Content-Type header, it can also include the character set encoding, which makes it more than just a MimeType specification. If MimeType is specified e.g. not None, that value is used. Otherwise, ContentType is used. If neither is given, the DEFAULT_CONTENT_TYPE setting is used.   */
  public java.lang.CharSequence getContentType() {
    return contentType;
  }

  /**
   * Sets the value of the 'contentType' field.
   * The type of the content contained within the document itself. ContentType is an alias for MimeType. Historically, this parameter was only called MimeType, but since this is actually the value included in the HTTP Content-Type header, it can also include the character set encoding, which makes it more than just a MimeType specification. If MimeType is specified e.g. not None, that value is used. Otherwise, ContentType is used. If neither is given, the DEFAULT_CONTENT_TYPE setting is used.   * @param value the value to set.
   */
  public void setContentType(java.lang.CharSequence value) {
    this.contentType = value;
    setDirty(10);
  }
  
  /**
   * Checks the dirty status of the 'contentType' field. A field is dirty if it represents a change that has not yet been written to the database.
   * The type of the content contained within the document itself. ContentType is an alias for MimeType. Historically, this parameter was only called MimeType, but since this is actually the value included in the HTTP Content-Type header, it can also include the character set encoding, which makes it more than just a MimeType specification. If MimeType is specified e.g. not None, that value is used. Otherwise, ContentType is used. If neither is given, the DEFAULT_CONTENT_TYPE setting is used.   * @param value the value to set.
   */
  public boolean isContentTypeDirty() {
    return isDirty(10);
  }

  /**
   * Gets the value of the 'prevSignature' field.
   * An implementation of a WebPage's previous signature from which it can be identified and referenced at any point in time. This can be used to uniquely identify WebPage deltas based on page fingerprints.   */
  public java.nio.ByteBuffer getPrevSignature() {
    return prevSignature;
  }

  /**
   * Sets the value of the 'prevSignature' field.
   * An implementation of a WebPage's previous signature from which it can be identified and referenced at any point in time. This can be used to uniquely identify WebPage deltas based on page fingerprints.   * @param value the value to set.
   */
  public void setPrevSignature(java.nio.ByteBuffer value) {
    this.prevSignature = value;
    setDirty(11);
  }
  
  /**
   * Checks the dirty status of the 'prevSignature' field. A field is dirty if it represents a change that has not yet been written to the database.
   * An implementation of a WebPage's previous signature from which it can be identified and referenced at any point in time. This can be used to uniquely identify WebPage deltas based on page fingerprints.   * @param value the value to set.
   */
  public boolean isPrevSignatureDirty() {
    return isDirty(11);
  }

  /**
   * Gets the value of the 'signature' field.
   * An implementation of a WebPage's signature from which it can be identified and referenced at any point in time. This is essentially the WebPage's fingerprint represnting its state for any point in time.   */
  public java.nio.ByteBuffer getSignature() {
    return signature;
  }

  /**
   * Sets the value of the 'signature' field.
   * An implementation of a WebPage's signature from which it can be identified and referenced at any point in time. This is essentially the WebPage's fingerprint represnting its state for any point in time.   * @param value the value to set.
   */
  public void setSignature(java.nio.ByteBuffer value) {
    this.signature = value;
    setDirty(12);
  }
  
  /**
   * Checks the dirty status of the 'signature' field. A field is dirty if it represents a change that has not yet been written to the database.
   * An implementation of a WebPage's signature from which it can be identified and referenced at any point in time. This is essentially the WebPage's fingerprint represnting its state for any point in time.   * @param value the value to set.
   */
  public boolean isSignatureDirty() {
    return isDirty(12);
  }

  /**
   * Gets the value of the 'title' field.
   * The title of the WebPage.   */
  public java.lang.CharSequence getTitle() {
    return title;
  }

  /**
   * Sets the value of the 'title' field.
   * The title of the WebPage.   * @param value the value to set.
   */
  public void setTitle(java.lang.CharSequence value) {
    this.title = value;
    setDirty(13);
  }
  
  /**
   * Checks the dirty status of the 'title' field. A field is dirty if it represents a change that has not yet been written to the database.
   * The title of the WebPage.   * @param value the value to set.
   */
  public boolean isTitleDirty() {
    return isDirty(13);
  }

  /**
   * Gets the value of the 'text' field.
   * The textual content of the WebPage devoid from native markup.   */
  public java.lang.CharSequence getText() {
    return text;
  }

  /**
   * Sets the value of the 'text' field.
   * The textual content of the WebPage devoid from native markup.   * @param value the value to set.
   */
  public void setText(java.lang.CharSequence value) {
    this.text = value;
    setDirty(14);
  }
  
  /**
   * Checks the dirty status of the 'text' field. A field is dirty if it represents a change that has not yet been written to the database.
   * The textual content of the WebPage devoid from native markup.   * @param value the value to set.
   */
  public boolean isTextDirty() {
    return isDirty(14);
  }

  /**
   * Gets the value of the 'parseStatus' field.
   */
  public org.apache.nutch.storage.ParseStatus getParseStatus() {
    return parseStatus;
  }

  /**
   * Sets the value of the 'parseStatus' field.
   * @param value the value to set.
   */
  public void setParseStatus(org.apache.nutch.storage.ParseStatus value) {
    this.parseStatus = value;
    setDirty(15);
  }
  
  /**
   * Checks the dirty status of the 'parseStatus' field. A field is dirty if it represents a change that has not yet been written to the database.
   * @param value the value to set.
   */
  public boolean isParseStatusDirty() {
    return isDirty(15);
  }

  /**
   * Gets the value of the 'score' field.
   * A score used to determine a WebPage's relevance within the web graph it is part of. This score may change over time based on graph characteristics.   */
  public java.lang.Float getScore() {
    return score;
  }

  /**
   * Sets the value of the 'score' field.
   * A score used to determine a WebPage's relevance within the web graph it is part of. This score may change over time based on graph characteristics.   * @param value the value to set.
   */
  public void setScore(java.lang.Float value) {
    this.score = value;
    setDirty(16);
  }
  
  /**
   * Checks the dirty status of the 'score' field. A field is dirty if it represents a change that has not yet been written to the database.
   * A score used to determine a WebPage's relevance within the web graph it is part of. This score may change over time based on graph characteristics.   * @param value the value to set.
   */
  public boolean isScoreDirty() {
    return isDirty(16);
  }

  /**
   * Gets the value of the 'reprUrl' field.
   * In the case where we are given two urls, a source and a destination of a redirect, we should determine and persist the representative url. The logic used to determine this is based largely on Yahoo!'s Slurp Crawler   */
  public java.lang.CharSequence getReprUrl() {
    return reprUrl;
  }

  /**
   * Sets the value of the 'reprUrl' field.
   * In the case where we are given two urls, a source and a destination of a redirect, we should determine and persist the representative url. The logic used to determine this is based largely on Yahoo!'s Slurp Crawler   * @param value the value to set.
   */
  public void setReprUrl(java.lang.CharSequence value) {
    this.reprUrl = value;
    setDirty(17);
  }
  
  /**
   * Checks the dirty status of the 'reprUrl' field. A field is dirty if it represents a change that has not yet been written to the database.
   * In the case where we are given two urls, a source and a destination of a redirect, we should determine and persist the representative url. The logic used to determine this is based largely on Yahoo!'s Slurp Crawler   * @param value the value to set.
   */
  public boolean isReprUrlDirty() {
    return isDirty(17);
  }

  /**
   * Gets the value of the 'headers' field.
   * Header information returned from the web server used to server the content which is subsequently fetched from. This includes keys such as TRANSFER_ENCODING, CONTENT_ENCODING, CONTENT_LANGUAGE, CONTENT_LENGTH, CONTENT_LOCATION, CONTENT_DISPOSITION, CONTENT_MD5, CONTENT_TYPE, LAST_MODIFIED and LOCATION.   */
  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getHeaders() {
    return headers;
  }

  /**
   * Sets the value of the 'headers' field.
   * Header information returned from the web server used to server the content which is subsequently fetched from. This includes keys such as TRANSFER_ENCODING, CONTENT_ENCODING, CONTENT_LANGUAGE, CONTENT_LENGTH, CONTENT_LOCATION, CONTENT_DISPOSITION, CONTENT_MD5, CONTENT_TYPE, LAST_MODIFIED and LOCATION.   * @param value the value to set.
   */
  public void setHeaders(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
    this.headers = (value instanceof org.apache.gora.persistency.Dirtyable) ? value : new org.apache.gora.persistency.impl.DirtyMapWrapper(value);
    setDirty(18);
  }
  
  /**
   * Checks the dirty status of the 'headers' field. A field is dirty if it represents a change that has not yet been written to the database.
   * Header information returned from the web server used to server the content which is subsequently fetched from. This includes keys such as TRANSFER_ENCODING, CONTENT_ENCODING, CONTENT_LANGUAGE, CONTENT_LENGTH, CONTENT_LOCATION, CONTENT_DISPOSITION, CONTENT_MD5, CONTENT_TYPE, LAST_MODIFIED and LOCATION.   * @param value the value to set.
   */
  public boolean isHeadersDirty() {
    return isDirty(18);
  }

  /**
   * Gets the value of the 'outlinks' field.
   * Embedded hyperlinks which direct outside of the current domain.   */
  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getOutlinks() {
    return outlinks;
  }

  /**
   * Sets the value of the 'outlinks' field.
   * Embedded hyperlinks which direct outside of the current domain.   * @param value the value to set.
   */
  public void setOutlinks(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
    this.outlinks = (value instanceof org.apache.gora.persistency.Dirtyable) ? value : new org.apache.gora.persistency.impl.DirtyMapWrapper(value);
    setDirty(19);
  }
  
  /**
   * Checks the dirty status of the 'outlinks' field. A field is dirty if it represents a change that has not yet been written to the database.
   * Embedded hyperlinks which direct outside of the current domain.   * @param value the value to set.
   */
  public boolean isOutlinksDirty() {
    return isDirty(19);
  }

  /**
   * Gets the value of the 'inlinks' field.
   * Embedded hyperlinks which link to pages within the current domain.   */
  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getInlinks() {
    return inlinks;
  }

  /**
   * Sets the value of the 'inlinks' field.
   * Embedded hyperlinks which link to pages within the current domain.   * @param value the value to set.
   */
  public void setInlinks(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
    this.inlinks = (value instanceof org.apache.gora.persistency.Dirtyable) ? value : new org.apache.gora.persistency.impl.DirtyMapWrapper(value);
    setDirty(20);
  }
  
  /**
   * Checks the dirty status of the 'inlinks' field. A field is dirty if it represents a change that has not yet been written to the database.
   * Embedded hyperlinks which link to pages within the current domain.   * @param value the value to set.
   */
  public boolean isInlinksDirty() {
    return isDirty(20);
  }

  /**
   * Gets the value of the 'markers' field.
   * Markers flags which represent user and machine decisions which have affected influenced a WebPage's current state. Markers can be system specific and user machine driven in nature. They are assigned to a WebPage on a job-by-job basis and thier values indicative of what actions should be associated with a WebPage.   */
  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getMarkers() {
    return markers;
  }

  /**
   * Sets the value of the 'markers' field.
   * Markers flags which represent user and machine decisions which have affected influenced a WebPage's current state. Markers can be system specific and user machine driven in nature. They are assigned to a WebPage on a job-by-job basis and thier values indicative of what actions should be associated with a WebPage.   * @param value the value to set.
   */
  public void setMarkers(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
    this.markers = (value instanceof org.apache.gora.persistency.Dirtyable) ? value : new org.apache.gora.persistency.impl.DirtyMapWrapper(value);
    setDirty(21);
  }
  
  /**
   * Checks the dirty status of the 'markers' field. A field is dirty if it represents a change that has not yet been written to the database.
   * Markers flags which represent user and machine decisions which have affected influenced a WebPage's current state. Markers can be system specific and user machine driven in nature. They are assigned to a WebPage on a job-by-job basis and thier values indicative of what actions should be associated with a WebPage.   * @param value the value to set.
   */
  public boolean isMarkersDirty() {
    return isDirty(21);
  }

  /**
   * Gets the value of the 'metadata' field.
   * A multi-valued metadata container used for storing everything from structured WebPage characterists, to ad-hoc extraction and metadata augmentation for any given WebPage.   */
  public java.util.Map<java.lang.CharSequence,java.nio.ByteBuffer> getMetadata() {
    return metadata;
  }

  /**
   * Sets the value of the 'metadata' field.
   * A multi-valued metadata container used for storing everything from structured WebPage characterists, to ad-hoc extraction and metadata augmentation for any given WebPage.   * @param value the value to set.
   */
  public void setMetadata(java.util.Map<java.lang.CharSequence,java.nio.ByteBuffer> value) {
    this.metadata = (value instanceof org.apache.gora.persistency.Dirtyable) ? value : new org.apache.gora.persistency.impl.DirtyMapWrapper(value);
    setDirty(22);
  }
  
  /**
   * Checks the dirty status of the 'metadata' field. A field is dirty if it represents a change that has not yet been written to the database.
   * A multi-valued metadata container used for storing everything from structured WebPage characterists, to ad-hoc extraction and metadata augmentation for any given WebPage.   * @param value the value to set.
   */
  public boolean isMetadataDirty() {
    return isDirty(22);
  }

  /**
   * Gets the value of the 'batchId' field.
   * A batchId that this WebPage is assigned to. WebPage's are fetched in batches, called fetchlists. Pages are partitioned but can always be associated and fetched alongside pages of similar value (within a crawl cycle) based on batchId.   */
  public java.lang.CharSequence getBatchId() {
    return batchId;
  }

  /**
   * Sets the value of the 'batchId' field.
   * A batchId that this WebPage is assigned to. WebPage's are fetched in batches, called fetchlists. Pages are partitioned but can always be associated and fetched alongside pages of similar value (within a crawl cycle) based on batchId.   * @param value the value to set.
   */
  public void setBatchId(java.lang.CharSequence value) {
    this.batchId = value;
    setDirty(23);
  }
  
  /**
   * Checks the dirty status of the 'batchId' field. A field is dirty if it represents a change that has not yet been written to the database.
   * A batchId that this WebPage is assigned to. WebPage's are fetched in batches, called fetchlists. Pages are partitioned but can always be associated and fetched alongside pages of similar value (within a crawl cycle) based on batchId.   * @param value the value to set.
   */
  public boolean isBatchIdDirty() {
    return isDirty(23);
  }

  /**
   * Gets the value of the 'sitemaps' field.
   * Sitemap urls in robot.txt   */
  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getSitemaps() {
    return sitemaps;
  }

  /**
   * Sets the value of the 'sitemaps' field.
   * Sitemap urls in robot.txt   * @param value the value to set.
   */
  public void setSitemaps(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
    this.sitemaps = (value instanceof org.apache.gora.persistency.Dirtyable) ? value : new org.apache.gora.persistency.impl.DirtyMapWrapper(value);
    setDirty(24);
  }
  
  /**
   * Checks the dirty status of the 'sitemaps' field. A field is dirty if it represents a change that has not yet been written to the database.
   * Sitemap urls in robot.txt   * @param value the value to set.
   */
  public boolean isSitemapsDirty() {
    return isDirty(24);
  }

  /**
   * Gets the value of the 'stmPriority' field.
   *    */
  public java.lang.Float getStmPriority() {
    return stmPriority;
  }

  /**
   * Sets the value of the 'stmPriority' field.
   *    * @param value the value to set.
   */
  public void setStmPriority(java.lang.Float value) {
    this.stmPriority = value;
    setDirty(25);
  }
  
  /**
   * Checks the dirty status of the 'stmPriority' field. A field is dirty if it represents a change that has not yet been written to the database.
   *    * @param value the value to set.
   */
  public boolean isStmPriorityDirty() {
    return isDirty(25);
  }

  /** Creates a new WebPage RecordBuilder */
  public static org.apache.nutch.storage.WebPage.Builder newBuilder() {
    return new org.apache.nutch.storage.WebPage.Builder();
  }
  
  /** Creates a new WebPage RecordBuilder by copying an existing Builder */
  public static org.apache.nutch.storage.WebPage.Builder newBuilder(org.apache.nutch.storage.WebPage.Builder other) {
    return new org.apache.nutch.storage.WebPage.Builder(other);
  }
  
  /** Creates a new WebPage RecordBuilder by copying an existing WebPage instance */
  public static org.apache.nutch.storage.WebPage.Builder newBuilder(org.apache.nutch.storage.WebPage other) {
    return new org.apache.nutch.storage.WebPage.Builder(other);
  }
  
  private static java.nio.ByteBuffer deepCopyToReadOnlyBuffer(
      java.nio.ByteBuffer input) {
    java.nio.ByteBuffer copy = java.nio.ByteBuffer.allocate(input.capacity());
    int position = input.position();
    input.reset();
    int mark = input.position();
    int limit = input.limit();
    input.rewind();
    input.limit(input.capacity());
    copy.put(input);
    input.rewind();
    copy.rewind();
    input.position(mark);
    input.mark();
    copy.position(mark);
    copy.mark();
    input.position(position);
    copy.position(position);
    input.limit(limit);
    copy.limit(limit);
    return copy.asReadOnlyBuffer();
  }
  
  /**
   * RecordBuilder for WebPage instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<WebPage>
    implements org.apache.avro.data.RecordBuilder<WebPage> {

    private java.lang.CharSequence baseUrl;
    private int status;
    private long fetchTime;
    private long prevFetchTime;
    private int fetchInterval;
    private int retriesSinceFetch;
    private long modifiedTime;
    private long prevModifiedTime;
    private org.apache.nutch.storage.ProtocolStatus protocolStatus;
    private java.nio.ByteBuffer content;
    private java.lang.CharSequence contentType;
    private java.nio.ByteBuffer prevSignature;
    private java.nio.ByteBuffer signature;
    private java.lang.CharSequence title;
    private java.lang.CharSequence text;
    private org.apache.nutch.storage.ParseStatus parseStatus;
    private float score;
    private java.lang.CharSequence reprUrl;
    private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> headers;
    private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> outlinks;
    private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> inlinks;
    private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> markers;
    private java.util.Map<java.lang.CharSequence,java.nio.ByteBuffer> metadata;
    private java.lang.CharSequence batchId;
    private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> sitemaps;
    private float stmPriority;

    /** Creates a new Builder */
    private Builder() {
      super(org.apache.nutch.storage.WebPage.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(org.apache.nutch.storage.WebPage.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing WebPage instance */
    private Builder(org.apache.nutch.storage.WebPage other) {
            super(org.apache.nutch.storage.WebPage.SCHEMA$);
      if (isValidValue(fields()[0], other.baseUrl)) {
        this.baseUrl = (java.lang.CharSequence) data().deepCopy(fields()[0].schema(), other.baseUrl);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.status)) {
        this.status = (java.lang.Integer) data().deepCopy(fields()[1].schema(), other.status);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.fetchTime)) {
        this.fetchTime = (java.lang.Long) data().deepCopy(fields()[2].schema(), other.fetchTime);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.prevFetchTime)) {
        this.prevFetchTime = (java.lang.Long) data().deepCopy(fields()[3].schema(), other.prevFetchTime);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.fetchInterval)) {
        this.fetchInterval = (java.lang.Integer) data().deepCopy(fields()[4].schema(), other.fetchInterval);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.retriesSinceFetch)) {
        this.retriesSinceFetch = (java.lang.Integer) data().deepCopy(fields()[5].schema(), other.retriesSinceFetch);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.modifiedTime)) {
        this.modifiedTime = (java.lang.Long) data().deepCopy(fields()[6].schema(), other.modifiedTime);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.prevModifiedTime)) {
        this.prevModifiedTime = (java.lang.Long) data().deepCopy(fields()[7].schema(), other.prevModifiedTime);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.protocolStatus)) {
        this.protocolStatus = (org.apache.nutch.storage.ProtocolStatus) data().deepCopy(fields()[8].schema(), other.protocolStatus);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.content)) {
        this.content = (java.nio.ByteBuffer) data().deepCopy(fields()[9].schema(), other.content);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.contentType)) {
        this.contentType = (java.lang.CharSequence) data().deepCopy(fields()[10].schema(), other.contentType);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.prevSignature)) {
        this.prevSignature = (java.nio.ByteBuffer) data().deepCopy(fields()[11].schema(), other.prevSignature);
        fieldSetFlags()[11] = true;
      }
      if (isValidValue(fields()[12], other.signature)) {
        this.signature = (java.nio.ByteBuffer) data().deepCopy(fields()[12].schema(), other.signature);
        fieldSetFlags()[12] = true;
      }
      if (isValidValue(fields()[13], other.title)) {
        this.title = (java.lang.CharSequence) data().deepCopy(fields()[13].schema(), other.title);
        fieldSetFlags()[13] = true;
      }
      if (isValidValue(fields()[14], other.text)) {
        this.text = (java.lang.CharSequence) data().deepCopy(fields()[14].schema(), other.text);
        fieldSetFlags()[14] = true;
      }
      if (isValidValue(fields()[15], other.parseStatus)) {
        this.parseStatus = (org.apache.nutch.storage.ParseStatus) data().deepCopy(fields()[15].schema(), other.parseStatus);
        fieldSetFlags()[15] = true;
      }
      if (isValidValue(fields()[16], other.score)) {
        this.score = (java.lang.Float) data().deepCopy(fields()[16].schema(), other.score);
        fieldSetFlags()[16] = true;
      }
      if (isValidValue(fields()[17], other.reprUrl)) {
        this.reprUrl = (java.lang.CharSequence) data().deepCopy(fields()[17].schema(), other.reprUrl);
        fieldSetFlags()[17] = true;
      }
      if (isValidValue(fields()[18], other.headers)) {
        this.headers = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) data().deepCopy(fields()[18].schema(), other.headers);
        fieldSetFlags()[18] = true;
      }
      if (isValidValue(fields()[19], other.outlinks)) {
        this.outlinks = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) data().deepCopy(fields()[19].schema(), other.outlinks);
        fieldSetFlags()[19] = true;
      }
      if (isValidValue(fields()[20], other.inlinks)) {
        this.inlinks = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) data().deepCopy(fields()[20].schema(), other.inlinks);
        fieldSetFlags()[20] = true;
      }
      if (isValidValue(fields()[21], other.markers)) {
        this.markers = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) data().deepCopy(fields()[21].schema(), other.markers);
        fieldSetFlags()[21] = true;
      }
      if (isValidValue(fields()[22], other.metadata)) {
        this.metadata = (java.util.Map<java.lang.CharSequence,java.nio.ByteBuffer>) data().deepCopy(fields()[22].schema(), other.metadata);
        fieldSetFlags()[22] = true;
      }
      if (isValidValue(fields()[23], other.batchId)) {
        this.batchId = (java.lang.CharSequence) data().deepCopy(fields()[23].schema(), other.batchId);
        fieldSetFlags()[23] = true;
      }
      if (isValidValue(fields()[24], other.sitemaps)) {
        this.sitemaps = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) data().deepCopy(fields()[24].schema(), other.sitemaps);
        fieldSetFlags()[24] = true;
      }
      if (isValidValue(fields()[25], other.stmPriority)) {
        this.stmPriority = (java.lang.Float) data().deepCopy(fields()[25].schema(), other.stmPriority);
        fieldSetFlags()[25] = true;
      }
    }

    /** Gets the value of the 'baseUrl' field */
    public java.lang.CharSequence getBaseUrl() {
      return baseUrl;
    }
    
    /** Sets the value of the 'baseUrl' field */
    public org.apache.nutch.storage.WebPage.Builder setBaseUrl(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.baseUrl = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'baseUrl' field has been set */
    public boolean hasBaseUrl() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'baseUrl' field */
    public org.apache.nutch.storage.WebPage.Builder clearBaseUrl() {
      baseUrl = null;
      fieldSetFlags()[0] = false;
      return this;
    }
    
    /** Gets the value of the 'status' field */
    public java.lang.Integer getStatus() {
      return status;
    }
    
    /** Sets the value of the 'status' field */
    public org.apache.nutch.storage.WebPage.Builder setStatus(int value) {
      validate(fields()[1], value);
      this.status = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'status' field has been set */
    public boolean hasStatus() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'status' field */
    public org.apache.nutch.storage.WebPage.Builder clearStatus() {
      fieldSetFlags()[1] = false;
      return this;
    }
    
    /** Gets the value of the 'fetchTime' field */
    public java.lang.Long getFetchTime() {
      return fetchTime;
    }
    
    /** Sets the value of the 'fetchTime' field */
    public org.apache.nutch.storage.WebPage.Builder setFetchTime(long value) {
      validate(fields()[2], value);
      this.fetchTime = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'fetchTime' field has been set */
    public boolean hasFetchTime() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'fetchTime' field */
    public org.apache.nutch.storage.WebPage.Builder clearFetchTime() {
      fieldSetFlags()[2] = false;
      return this;
    }
    
    /** Gets the value of the 'prevFetchTime' field */
    public java.lang.Long getPrevFetchTime() {
      return prevFetchTime;
    }
    
    /** Sets the value of the 'prevFetchTime' field */
    public org.apache.nutch.storage.WebPage.Builder setPrevFetchTime(long value) {
      validate(fields()[3], value);
      this.prevFetchTime = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'prevFetchTime' field has been set */
    public boolean hasPrevFetchTime() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'prevFetchTime' field */
    public org.apache.nutch.storage.WebPage.Builder clearPrevFetchTime() {
      fieldSetFlags()[3] = false;
      return this;
    }
    
    /** Gets the value of the 'fetchInterval' field */
    public java.lang.Integer getFetchInterval() {
      return fetchInterval;
    }
    
    /** Sets the value of the 'fetchInterval' field */
    public org.apache.nutch.storage.WebPage.Builder setFetchInterval(int value) {
      validate(fields()[4], value);
      this.fetchInterval = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'fetchInterval' field has been set */
    public boolean hasFetchInterval() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'fetchInterval' field */
    public org.apache.nutch.storage.WebPage.Builder clearFetchInterval() {
      fieldSetFlags()[4] = false;
      return this;
    }
    
    /** Gets the value of the 'retriesSinceFetch' field */
    public java.lang.Integer getRetriesSinceFetch() {
      return retriesSinceFetch;
    }
    
    /** Sets the value of the 'retriesSinceFetch' field */
    public org.apache.nutch.storage.WebPage.Builder setRetriesSinceFetch(int value) {
      validate(fields()[5], value);
      this.retriesSinceFetch = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'retriesSinceFetch' field has been set */
    public boolean hasRetriesSinceFetch() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'retriesSinceFetch' field */
    public org.apache.nutch.storage.WebPage.Builder clearRetriesSinceFetch() {
      fieldSetFlags()[5] = false;
      return this;
    }
    
    /** Gets the value of the 'modifiedTime' field */
    public java.lang.Long getModifiedTime() {
      return modifiedTime;
    }
    
    /** Sets the value of the 'modifiedTime' field */
    public org.apache.nutch.storage.WebPage.Builder setModifiedTime(long value) {
      validate(fields()[6], value);
      this.modifiedTime = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'modifiedTime' field has been set */
    public boolean hasModifiedTime() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'modifiedTime' field */
    public org.apache.nutch.storage.WebPage.Builder clearModifiedTime() {
      fieldSetFlags()[6] = false;
      return this;
    }
    
    /** Gets the value of the 'prevModifiedTime' field */
    public java.lang.Long getPrevModifiedTime() {
      return prevModifiedTime;
    }
    
    /** Sets the value of the 'prevModifiedTime' field */
    public org.apache.nutch.storage.WebPage.Builder setPrevModifiedTime(long value) {
      validate(fields()[7], value);
      this.prevModifiedTime = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'prevModifiedTime' field has been set */
    public boolean hasPrevModifiedTime() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'prevModifiedTime' field */
    public org.apache.nutch.storage.WebPage.Builder clearPrevModifiedTime() {
      fieldSetFlags()[7] = false;
      return this;
    }
    
    /** Gets the value of the 'protocolStatus' field */
    public org.apache.nutch.storage.ProtocolStatus getProtocolStatus() {
      return protocolStatus;
    }
    
    /** Sets the value of the 'protocolStatus' field */
    public org.apache.nutch.storage.WebPage.Builder setProtocolStatus(org.apache.nutch.storage.ProtocolStatus value) {
      validate(fields()[8], value);
      this.protocolStatus = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'protocolStatus' field has been set */
    public boolean hasProtocolStatus() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'protocolStatus' field */
    public org.apache.nutch.storage.WebPage.Builder clearProtocolStatus() {
      protocolStatus = null;
      fieldSetFlags()[8] = false;
      return this;
    }
    
    /** Gets the value of the 'content' field */
    public java.nio.ByteBuffer getContent() {
      return content;
    }
    
    /** Sets the value of the 'content' field */
    public org.apache.nutch.storage.WebPage.Builder setContent(java.nio.ByteBuffer value) {
      validate(fields()[9], value);
      this.content = value;
      fieldSetFlags()[9] = true;
      return this; 
    }
    
    /** Checks whether the 'content' field has been set */
    public boolean hasContent() {
      return fieldSetFlags()[9];
    }
    
    /** Clears the value of the 'content' field */
    public org.apache.nutch.storage.WebPage.Builder clearContent() {
      content = null;
      fieldSetFlags()[9] = false;
      return this;
    }
    
    /** Gets the value of the 'contentType' field */
    public java.lang.CharSequence getContentType() {
      return contentType;
    }
    
    /** Sets the value of the 'contentType' field */
    public org.apache.nutch.storage.WebPage.Builder setContentType(java.lang.CharSequence value) {
      validate(fields()[10], value);
      this.contentType = value;
      fieldSetFlags()[10] = true;
      return this; 
    }
    
    /** Checks whether the 'contentType' field has been set */
    public boolean hasContentType() {
      return fieldSetFlags()[10];
    }
    
    /** Clears the value of the 'contentType' field */
    public org.apache.nutch.storage.WebPage.Builder clearContentType() {
      contentType = null;
      fieldSetFlags()[10] = false;
      return this;
    }
    
    /** Gets the value of the 'prevSignature' field */
    public java.nio.ByteBuffer getPrevSignature() {
      return prevSignature;
    }
    
    /** Sets the value of the 'prevSignature' field */
    public org.apache.nutch.storage.WebPage.Builder setPrevSignature(java.nio.ByteBuffer value) {
      validate(fields()[11], value);
      this.prevSignature = value;
      fieldSetFlags()[11] = true;
      return this; 
    }
    
    /** Checks whether the 'prevSignature' field has been set */
    public boolean hasPrevSignature() {
      return fieldSetFlags()[11];
    }
    
    /** Clears the value of the 'prevSignature' field */
    public org.apache.nutch.storage.WebPage.Builder clearPrevSignature() {
      prevSignature = null;
      fieldSetFlags()[11] = false;
      return this;
    }
    
    /** Gets the value of the 'signature' field */
    public java.nio.ByteBuffer getSignature() {
      return signature;
    }
    
    /** Sets the value of the 'signature' field */
    public org.apache.nutch.storage.WebPage.Builder setSignature(java.nio.ByteBuffer value) {
      validate(fields()[12], value);
      this.signature = value;
      fieldSetFlags()[12] = true;
      return this; 
    }
    
    /** Checks whether the 'signature' field has been set */
    public boolean hasSignature() {
      return fieldSetFlags()[12];
    }
    
    /** Clears the value of the 'signature' field */
    public org.apache.nutch.storage.WebPage.Builder clearSignature() {
      signature = null;
      fieldSetFlags()[12] = false;
      return this;
    }
    
    /** Gets the value of the 'title' field */
    public java.lang.CharSequence getTitle() {
      return title;
    }
    
    /** Sets the value of the 'title' field */
    public org.apache.nutch.storage.WebPage.Builder setTitle(java.lang.CharSequence value) {
      validate(fields()[13], value);
      this.title = value;
      fieldSetFlags()[13] = true;
      return this; 
    }
    
    /** Checks whether the 'title' field has been set */
    public boolean hasTitle() {
      return fieldSetFlags()[13];
    }
    
    /** Clears the value of the 'title' field */
    public org.apache.nutch.storage.WebPage.Builder clearTitle() {
      title = null;
      fieldSetFlags()[13] = false;
      return this;
    }
    
    /** Gets the value of the 'text' field */
    public java.lang.CharSequence getText() {
      return text;
    }
    
    /** Sets the value of the 'text' field */
    public org.apache.nutch.storage.WebPage.Builder setText(java.lang.CharSequence value) {
      validate(fields()[14], value);
      this.text = value;
      fieldSetFlags()[14] = true;
      return this; 
    }
    
    /** Checks whether the 'text' field has been set */
    public boolean hasText() {
      return fieldSetFlags()[14];
    }
    
    /** Clears the value of the 'text' field */
    public org.apache.nutch.storage.WebPage.Builder clearText() {
      text = null;
      fieldSetFlags()[14] = false;
      return this;
    }
    
    /** Gets the value of the 'parseStatus' field */
    public org.apache.nutch.storage.ParseStatus getParseStatus() {
      return parseStatus;
    }
    
    /** Sets the value of the 'parseStatus' field */
    public org.apache.nutch.storage.WebPage.Builder setParseStatus(org.apache.nutch.storage.ParseStatus value) {
      validate(fields()[15], value);
      this.parseStatus = value;
      fieldSetFlags()[15] = true;
      return this; 
    }
    
    /** Checks whether the 'parseStatus' field has been set */
    public boolean hasParseStatus() {
      return fieldSetFlags()[15];
    }
    
    /** Clears the value of the 'parseStatus' field */
    public org.apache.nutch.storage.WebPage.Builder clearParseStatus() {
      parseStatus = null;
      fieldSetFlags()[15] = false;
      return this;
    }
    
    /** Gets the value of the 'score' field */
    public java.lang.Float getScore() {
      return score;
    }
    
    /** Sets the value of the 'score' field */
    public org.apache.nutch.storage.WebPage.Builder setScore(float value) {
      validate(fields()[16], value);
      this.score = value;
      fieldSetFlags()[16] = true;
      return this; 
    }
    
    /** Checks whether the 'score' field has been set */
    public boolean hasScore() {
      return fieldSetFlags()[16];
    }
    
    /** Clears the value of the 'score' field */
    public org.apache.nutch.storage.WebPage.Builder clearScore() {
      fieldSetFlags()[16] = false;
      return this;
    }
    
    /** Gets the value of the 'reprUrl' field */
    public java.lang.CharSequence getReprUrl() {
      return reprUrl;
    }
    
    /** Sets the value of the 'reprUrl' field */
    public org.apache.nutch.storage.WebPage.Builder setReprUrl(java.lang.CharSequence value) {
      validate(fields()[17], value);
      this.reprUrl = value;
      fieldSetFlags()[17] = true;
      return this; 
    }
    
    /** Checks whether the 'reprUrl' field has been set */
    public boolean hasReprUrl() {
      return fieldSetFlags()[17];
    }
    
    /** Clears the value of the 'reprUrl' field */
    public org.apache.nutch.storage.WebPage.Builder clearReprUrl() {
      reprUrl = null;
      fieldSetFlags()[17] = false;
      return this;
    }
    
    /** Gets the value of the 'headers' field */
    public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getHeaders() {
      return headers;
    }
    
    /** Sets the value of the 'headers' field */
    public org.apache.nutch.storage.WebPage.Builder setHeaders(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
      validate(fields()[18], value);
      this.headers = value;
      fieldSetFlags()[18] = true;
      return this; 
    }
    
    /** Checks whether the 'headers' field has been set */
    public boolean hasHeaders() {
      return fieldSetFlags()[18];
    }
    
    /** Clears the value of the 'headers' field */
    public org.apache.nutch.storage.WebPage.Builder clearHeaders() {
      headers = null;
      fieldSetFlags()[18] = false;
      return this;
    }
    
    /** Gets the value of the 'outlinks' field */
    public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getOutlinks() {
      return outlinks;
    }
    
    /** Sets the value of the 'outlinks' field */
    public org.apache.nutch.storage.WebPage.Builder setOutlinks(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
      validate(fields()[19], value);
      this.outlinks = value;
      fieldSetFlags()[19] = true;
      return this; 
    }
    
    /** Checks whether the 'outlinks' field has been set */
    public boolean hasOutlinks() {
      return fieldSetFlags()[19];
    }
    
    /** Clears the value of the 'outlinks' field */
    public org.apache.nutch.storage.WebPage.Builder clearOutlinks() {
      outlinks = null;
      fieldSetFlags()[19] = false;
      return this;
    }
    
    /** Gets the value of the 'inlinks' field */
    public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getInlinks() {
      return inlinks;
    }
    
    /** Sets the value of the 'inlinks' field */
    public org.apache.nutch.storage.WebPage.Builder setInlinks(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
      validate(fields()[20], value);
      this.inlinks = value;
      fieldSetFlags()[20] = true;
      return this; 
    }
    
    /** Checks whether the 'inlinks' field has been set */
    public boolean hasInlinks() {
      return fieldSetFlags()[20];
    }
    
    /** Clears the value of the 'inlinks' field */
    public org.apache.nutch.storage.WebPage.Builder clearInlinks() {
      inlinks = null;
      fieldSetFlags()[20] = false;
      return this;
    }
    
    /** Gets the value of the 'markers' field */
    public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getMarkers() {
      return markers;
    }
    
    /** Sets the value of the 'markers' field */
    public org.apache.nutch.storage.WebPage.Builder setMarkers(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
      validate(fields()[21], value);
      this.markers = value;
      fieldSetFlags()[21] = true;
      return this; 
    }
    
    /** Checks whether the 'markers' field has been set */
    public boolean hasMarkers() {
      return fieldSetFlags()[21];
    }
    
    /** Clears the value of the 'markers' field */
    public org.apache.nutch.storage.WebPage.Builder clearMarkers() {
      markers = null;
      fieldSetFlags()[21] = false;
      return this;
    }
    
    /** Gets the value of the 'metadata' field */
    public java.util.Map<java.lang.CharSequence,java.nio.ByteBuffer> getMetadata() {
      return metadata;
    }
    
    /** Sets the value of the 'metadata' field */
    public org.apache.nutch.storage.WebPage.Builder setMetadata(java.util.Map<java.lang.CharSequence,java.nio.ByteBuffer> value) {
      validate(fields()[22], value);
      this.metadata = value;
      fieldSetFlags()[22] = true;
      return this; 
    }
    
    /** Checks whether the 'metadata' field has been set */
    public boolean hasMetadata() {
      return fieldSetFlags()[22];
    }
    
    /** Clears the value of the 'metadata' field */
    public org.apache.nutch.storage.WebPage.Builder clearMetadata() {
      metadata = null;
      fieldSetFlags()[22] = false;
      return this;
    }
    
    /** Gets the value of the 'batchId' field */
    public java.lang.CharSequence getBatchId() {
      return batchId;
    }
    
    /** Sets the value of the 'batchId' field */
    public org.apache.nutch.storage.WebPage.Builder setBatchId(java.lang.CharSequence value) {
      validate(fields()[23], value);
      this.batchId = value;
      fieldSetFlags()[23] = true;
      return this; 
    }
    
    /** Checks whether the 'batchId' field has been set */
    public boolean hasBatchId() {
      return fieldSetFlags()[23];
    }
    
    /** Clears the value of the 'batchId' field */
    public org.apache.nutch.storage.WebPage.Builder clearBatchId() {
      batchId = null;
      fieldSetFlags()[23] = false;
      return this;
    }
    
    /** Gets the value of the 'sitemaps' field */
    public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getSitemaps() {
      return sitemaps;
    }
    
    /** Sets the value of the 'sitemaps' field */
    public org.apache.nutch.storage.WebPage.Builder setSitemaps(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
      validate(fields()[24], value);
      this.sitemaps = value;
      fieldSetFlags()[24] = true;
      return this; 
    }
    
    /** Checks whether the 'sitemaps' field has been set */
    public boolean hasSitemaps() {
      return fieldSetFlags()[24];
    }
    
    /** Clears the value of the 'sitemaps' field */
    public org.apache.nutch.storage.WebPage.Builder clearSitemaps() {
      sitemaps = null;
      fieldSetFlags()[24] = false;
      return this;
    }
    
    /** Gets the value of the 'stmPriority' field */
    public java.lang.Float getStmPriority() {
      return stmPriority;
    }
    
    /** Sets the value of the 'stmPriority' field */
    public org.apache.nutch.storage.WebPage.Builder setStmPriority(float value) {
      validate(fields()[25], value);
      this.stmPriority = value;
      fieldSetFlags()[25] = true;
      return this; 
    }
    
    /** Checks whether the 'stmPriority' field has been set */
    public boolean hasStmPriority() {
      return fieldSetFlags()[25];
    }
    
    /** Clears the value of the 'stmPriority' field */
    public org.apache.nutch.storage.WebPage.Builder clearStmPriority() {
      fieldSetFlags()[25] = false;
      return this;
    }
    
    @Override
    public WebPage build() {
      try {
        WebPage record = new WebPage();
        record.baseUrl = fieldSetFlags()[0] ? this.baseUrl : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.status = fieldSetFlags()[1] ? this.status : (java.lang.Integer) defaultValue(fields()[1]);
        record.fetchTime = fieldSetFlags()[2] ? this.fetchTime : (java.lang.Long) defaultValue(fields()[2]);
        record.prevFetchTime = fieldSetFlags()[3] ? this.prevFetchTime : (java.lang.Long) defaultValue(fields()[3]);
        record.fetchInterval = fieldSetFlags()[4] ? this.fetchInterval : (java.lang.Integer) defaultValue(fields()[4]);
        record.retriesSinceFetch = fieldSetFlags()[5] ? this.retriesSinceFetch : (java.lang.Integer) defaultValue(fields()[5]);
        record.modifiedTime = fieldSetFlags()[6] ? this.modifiedTime : (java.lang.Long) defaultValue(fields()[6]);
        record.prevModifiedTime = fieldSetFlags()[7] ? this.prevModifiedTime : (java.lang.Long) defaultValue(fields()[7]);
        record.protocolStatus = fieldSetFlags()[8] ? this.protocolStatus : (org.apache.nutch.storage.ProtocolStatus) defaultValue(fields()[8]);
        record.content = fieldSetFlags()[9] ? this.content : (java.nio.ByteBuffer) defaultValue(fields()[9]);
        record.contentType = fieldSetFlags()[10] ? this.contentType : (java.lang.CharSequence) defaultValue(fields()[10]);
        record.prevSignature = fieldSetFlags()[11] ? this.prevSignature : (java.nio.ByteBuffer) defaultValue(fields()[11]);
        record.signature = fieldSetFlags()[12] ? this.signature : (java.nio.ByteBuffer) defaultValue(fields()[12]);
        record.title = fieldSetFlags()[13] ? this.title : (java.lang.CharSequence) defaultValue(fields()[13]);
        record.text = fieldSetFlags()[14] ? this.text : (java.lang.CharSequence) defaultValue(fields()[14]);
        record.parseStatus = fieldSetFlags()[15] ? this.parseStatus : (org.apache.nutch.storage.ParseStatus) defaultValue(fields()[15]);
        record.score = fieldSetFlags()[16] ? this.score : (java.lang.Float) defaultValue(fields()[16]);
        record.reprUrl = fieldSetFlags()[17] ? this.reprUrl : (java.lang.CharSequence) defaultValue(fields()[17]);
        record.headers = fieldSetFlags()[18] ? this.headers : (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) new org.apache.gora.persistency.impl.DirtyMapWrapper((java.util.Map)defaultValue(fields()[18]));
        record.outlinks = fieldSetFlags()[19] ? this.outlinks : (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) new org.apache.gora.persistency.impl.DirtyMapWrapper((java.util.Map)defaultValue(fields()[19]));
        record.inlinks = fieldSetFlags()[20] ? this.inlinks : (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) new org.apache.gora.persistency.impl.DirtyMapWrapper((java.util.Map)defaultValue(fields()[20]));
        record.markers = fieldSetFlags()[21] ? this.markers : (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) new org.apache.gora.persistency.impl.DirtyMapWrapper((java.util.Map)defaultValue(fields()[21]));
        record.metadata = fieldSetFlags()[22] ? this.metadata : (java.util.Map<java.lang.CharSequence,java.nio.ByteBuffer>) new org.apache.gora.persistency.impl.DirtyMapWrapper((java.util.Map)defaultValue(fields()[22]));
        record.batchId = fieldSetFlags()[23] ? this.batchId : (java.lang.CharSequence) defaultValue(fields()[23]);
        record.sitemaps = fieldSetFlags()[24] ? this.sitemaps : (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) new org.apache.gora.persistency.impl.DirtyMapWrapper((java.util.Map)defaultValue(fields()[24]));
        record.stmPriority = fieldSetFlags()[25] ? this.stmPriority : (java.lang.Float) defaultValue(fields()[25]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
  
  public WebPage.Tombstone getTombstone(){
  	return TOMBSTONE;
  }

  public WebPage newInstance(){
    return newBuilder().build();
  }

  private static final Tombstone TOMBSTONE = new Tombstone();
  
  public static final class Tombstone extends WebPage implements org.apache.gora.persistency.Tombstone {
  
      private Tombstone() { }
  
	  		  /**
	   * Gets the value of the 'baseUrl' field.
	   * The original associated with this WebPage.	   */
	  public java.lang.CharSequence getBaseUrl() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'baseUrl' field.
	   * The original associated with this WebPage.	   * @param value the value to set.
	   */
	  public void setBaseUrl(java.lang.CharSequence value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'baseUrl' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * The original associated with this WebPage.	   * @param value the value to set.
	   */
	  public boolean isBaseUrlDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'status' field.
	   * A crawl status associated with the WebPage, can be of value STATUS_UNFETCHED - WebPage was not fetched yet, STATUS_FETCHED - WebPage was successfully fetched, STATUS_GONE - WebPage no longer exists, STATUS_REDIR_TEMP - WebPage temporarily redirects to other page, STATUS_REDIR_PERM - WebPage permanently redirects to other page, STATUS_RETRY - Fetching unsuccessful, needs to be retried e.g. transient errors and STATUS_NOTMODIFIED - fetching successful - page is not modified	   */
	  public java.lang.Integer getStatus() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'status' field.
	   * A crawl status associated with the WebPage, can be of value STATUS_UNFETCHED - WebPage was not fetched yet, STATUS_FETCHED - WebPage was successfully fetched, STATUS_GONE - WebPage no longer exists, STATUS_REDIR_TEMP - WebPage temporarily redirects to other page, STATUS_REDIR_PERM - WebPage permanently redirects to other page, STATUS_RETRY - Fetching unsuccessful, needs to be retried e.g. transient errors and STATUS_NOTMODIFIED - fetching successful - page is not modified	   * @param value the value to set.
	   */
	  public void setStatus(java.lang.Integer value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'status' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * A crawl status associated with the WebPage, can be of value STATUS_UNFETCHED - WebPage was not fetched yet, STATUS_FETCHED - WebPage was successfully fetched, STATUS_GONE - WebPage no longer exists, STATUS_REDIR_TEMP - WebPage temporarily redirects to other page, STATUS_REDIR_PERM - WebPage permanently redirects to other page, STATUS_RETRY - Fetching unsuccessful, needs to be retried e.g. transient errors and STATUS_NOTMODIFIED - fetching successful - page is not modified	   * @param value the value to set.
	   */
	  public boolean isStatusDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'fetchTime' field.
	   * The system time in milliseconds for when the page was fetched.	   */
	  public java.lang.Long getFetchTime() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'fetchTime' field.
	   * The system time in milliseconds for when the page was fetched.	   * @param value the value to set.
	   */
	  public void setFetchTime(java.lang.Long value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'fetchTime' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * The system time in milliseconds for when the page was fetched.	   * @param value the value to set.
	   */
	  public boolean isFetchTimeDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'prevFetchTime' field.
	   * The system time in milliseconds for when the page was last fetched if it was previously fetched which can be used to calculate time delta within a fetching schedule implementation	   */
	  public java.lang.Long getPrevFetchTime() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'prevFetchTime' field.
	   * The system time in milliseconds for when the page was last fetched if it was previously fetched which can be used to calculate time delta within a fetching schedule implementation	   * @param value the value to set.
	   */
	  public void setPrevFetchTime(java.lang.Long value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'prevFetchTime' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * The system time in milliseconds for when the page was last fetched if it was previously fetched which can be used to calculate time delta within a fetching schedule implementation	   * @param value the value to set.
	   */
	  public boolean isPrevFetchTimeDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'fetchInterval' field.
	   * The default number of seconds between re-fetches of a page. The default is considered as 30 days unless a custom fetch schedle is implemented.	   */
	  public java.lang.Integer getFetchInterval() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'fetchInterval' field.
	   * The default number of seconds between re-fetches of a page. The default is considered as 30 days unless a custom fetch schedle is implemented.	   * @param value the value to set.
	   */
	  public void setFetchInterval(java.lang.Integer value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'fetchInterval' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * The default number of seconds between re-fetches of a page. The default is considered as 30 days unless a custom fetch schedle is implemented.	   * @param value the value to set.
	   */
	  public boolean isFetchIntervalDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'retriesSinceFetch' field.
	   * The number of retried attempts at fetching the WebPage since it was last successfully fetched.	   */
	  public java.lang.Integer getRetriesSinceFetch() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'retriesSinceFetch' field.
	   * The number of retried attempts at fetching the WebPage since it was last successfully fetched.	   * @param value the value to set.
	   */
	  public void setRetriesSinceFetch(java.lang.Integer value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'retriesSinceFetch' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * The number of retried attempts at fetching the WebPage since it was last successfully fetched.	   * @param value the value to set.
	   */
	  public boolean isRetriesSinceFetchDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'modifiedTime' field.
	   * The system time in milliseconds for when this WebPage was modified by the WebPage author, if this is not available we default to the server for this information. This is important to understand the changing nature of the WebPage.	   */
	  public java.lang.Long getModifiedTime() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'modifiedTime' field.
	   * The system time in milliseconds for when this WebPage was modified by the WebPage author, if this is not available we default to the server for this information. This is important to understand the changing nature of the WebPage.	   * @param value the value to set.
	   */
	  public void setModifiedTime(java.lang.Long value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'modifiedTime' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * The system time in milliseconds for when this WebPage was modified by the WebPage author, if this is not available we default to the server for this information. This is important to understand the changing nature of the WebPage.	   * @param value the value to set.
	   */
	  public boolean isModifiedTimeDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'prevModifiedTime' field.
	   * The system time in milliseconds for when this WebPage was previously modified by the author, if this is not available then we default to the server for this information. This is important to understand the changing nature of a WebPage.	   */
	  public java.lang.Long getPrevModifiedTime() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'prevModifiedTime' field.
	   * The system time in milliseconds for when this WebPage was previously modified by the author, if this is not available then we default to the server for this information. This is important to understand the changing nature of a WebPage.	   * @param value the value to set.
	   */
	  public void setPrevModifiedTime(java.lang.Long value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'prevModifiedTime' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * The system time in milliseconds for when this WebPage was previously modified by the author, if this is not available then we default to the server for this information. This is important to understand the changing nature of a WebPage.	   * @param value the value to set.
	   */
	  public boolean isPrevModifiedTimeDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'protocolStatus' field.
		   */
	  public org.apache.nutch.storage.ProtocolStatus getProtocolStatus() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'protocolStatus' field.
		   * @param value the value to set.
	   */
	  public void setProtocolStatus(org.apache.nutch.storage.ProtocolStatus value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'protocolStatus' field. A field is dirty if it represents a change that has not yet been written to the database.
		   * @param value the value to set.
	   */
	  public boolean isProtocolStatusDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'content' field.
	   * The entire raw document content e.g. raw XHTML	   */
	  public java.nio.ByteBuffer getContent() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'content' field.
	   * The entire raw document content e.g. raw XHTML	   * @param value the value to set.
	   */
	  public void setContent(java.nio.ByteBuffer value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'content' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * The entire raw document content e.g. raw XHTML	   * @param value the value to set.
	   */
	  public boolean isContentDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'contentType' field.
	   * The type of the content contained within the document itself. ContentType is an alias for MimeType. Historically, this parameter was only called MimeType, but since this is actually the value included in the HTTP Content-Type header, it can also include the character set encoding, which makes it more than just a MimeType specification. If MimeType is specified e.g. not None, that value is used. Otherwise, ContentType is used. If neither is given, the DEFAULT_CONTENT_TYPE setting is used.	   */
	  public java.lang.CharSequence getContentType() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'contentType' field.
	   * The type of the content contained within the document itself. ContentType is an alias for MimeType. Historically, this parameter was only called MimeType, but since this is actually the value included in the HTTP Content-Type header, it can also include the character set encoding, which makes it more than just a MimeType specification. If MimeType is specified e.g. not None, that value is used. Otherwise, ContentType is used. If neither is given, the DEFAULT_CONTENT_TYPE setting is used.	   * @param value the value to set.
	   */
	  public void setContentType(java.lang.CharSequence value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'contentType' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * The type of the content contained within the document itself. ContentType is an alias for MimeType. Historically, this parameter was only called MimeType, but since this is actually the value included in the HTTP Content-Type header, it can also include the character set encoding, which makes it more than just a MimeType specification. If MimeType is specified e.g. not None, that value is used. Otherwise, ContentType is used. If neither is given, the DEFAULT_CONTENT_TYPE setting is used.	   * @param value the value to set.
	   */
	  public boolean isContentTypeDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'prevSignature' field.
	   * An implementation of a WebPage's previous signature from which it can be identified and referenced at any point in time. This can be used to uniquely identify WebPage deltas based on page fingerprints.	   */
	  public java.nio.ByteBuffer getPrevSignature() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'prevSignature' field.
	   * An implementation of a WebPage's previous signature from which it can be identified and referenced at any point in time. This can be used to uniquely identify WebPage deltas based on page fingerprints.	   * @param value the value to set.
	   */
	  public void setPrevSignature(java.nio.ByteBuffer value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'prevSignature' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * An implementation of a WebPage's previous signature from which it can be identified and referenced at any point in time. This can be used to uniquely identify WebPage deltas based on page fingerprints.	   * @param value the value to set.
	   */
	  public boolean isPrevSignatureDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'signature' field.
	   * An implementation of a WebPage's signature from which it can be identified and referenced at any point in time. This is essentially the WebPage's fingerprint represnting its state for any point in time.	   */
	  public java.nio.ByteBuffer getSignature() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'signature' field.
	   * An implementation of a WebPage's signature from which it can be identified and referenced at any point in time. This is essentially the WebPage's fingerprint represnting its state for any point in time.	   * @param value the value to set.
	   */
	  public void setSignature(java.nio.ByteBuffer value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'signature' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * An implementation of a WebPage's signature from which it can be identified and referenced at any point in time. This is essentially the WebPage's fingerprint represnting its state for any point in time.	   * @param value the value to set.
	   */
	  public boolean isSignatureDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'title' field.
	   * The title of the WebPage.	   */
	  public java.lang.CharSequence getTitle() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'title' field.
	   * The title of the WebPage.	   * @param value the value to set.
	   */
	  public void setTitle(java.lang.CharSequence value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'title' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * The title of the WebPage.	   * @param value the value to set.
	   */
	  public boolean isTitleDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'text' field.
	   * The textual content of the WebPage devoid from native markup.	   */
	  public java.lang.CharSequence getText() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'text' field.
	   * The textual content of the WebPage devoid from native markup.	   * @param value the value to set.
	   */
	  public void setText(java.lang.CharSequence value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'text' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * The textual content of the WebPage devoid from native markup.	   * @param value the value to set.
	   */
	  public boolean isTextDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'parseStatus' field.
		   */
	  public org.apache.nutch.storage.ParseStatus getParseStatus() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'parseStatus' field.
		   * @param value the value to set.
	   */
	  public void setParseStatus(org.apache.nutch.storage.ParseStatus value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'parseStatus' field. A field is dirty if it represents a change that has not yet been written to the database.
		   * @param value the value to set.
	   */
	  public boolean isParseStatusDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'score' field.
	   * A score used to determine a WebPage's relevance within the web graph it is part of. This score may change over time based on graph characteristics.	   */
	  public java.lang.Float getScore() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'score' field.
	   * A score used to determine a WebPage's relevance within the web graph it is part of. This score may change over time based on graph characteristics.	   * @param value the value to set.
	   */
	  public void setScore(java.lang.Float value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'score' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * A score used to determine a WebPage's relevance within the web graph it is part of. This score may change over time based on graph characteristics.	   * @param value the value to set.
	   */
	  public boolean isScoreDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'reprUrl' field.
	   * In the case where we are given two urls, a source and a destination of a redirect, we should determine and persist the representative url. The logic used to determine this is based largely on Yahoo!'s Slurp Crawler	   */
	  public java.lang.CharSequence getReprUrl() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'reprUrl' field.
	   * In the case where we are given two urls, a source and a destination of a redirect, we should determine and persist the representative url. The logic used to determine this is based largely on Yahoo!'s Slurp Crawler	   * @param value the value to set.
	   */
	  public void setReprUrl(java.lang.CharSequence value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'reprUrl' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * In the case where we are given two urls, a source and a destination of a redirect, we should determine and persist the representative url. The logic used to determine this is based largely on Yahoo!'s Slurp Crawler	   * @param value the value to set.
	   */
	  public boolean isReprUrlDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'headers' field.
	   * Header information returned from the web server used to server the content which is subsequently fetched from. This includes keys such as TRANSFER_ENCODING, CONTENT_ENCODING, CONTENT_LANGUAGE, CONTENT_LENGTH, CONTENT_LOCATION, CONTENT_DISPOSITION, CONTENT_MD5, CONTENT_TYPE, LAST_MODIFIED and LOCATION.	   */
	  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getHeaders() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'headers' field.
	   * Header information returned from the web server used to server the content which is subsequently fetched from. This includes keys such as TRANSFER_ENCODING, CONTENT_ENCODING, CONTENT_LANGUAGE, CONTENT_LENGTH, CONTENT_LOCATION, CONTENT_DISPOSITION, CONTENT_MD5, CONTENT_TYPE, LAST_MODIFIED and LOCATION.	   * @param value the value to set.
	   */
	  public void setHeaders(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'headers' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * Header information returned from the web server used to server the content which is subsequently fetched from. This includes keys such as TRANSFER_ENCODING, CONTENT_ENCODING, CONTENT_LANGUAGE, CONTENT_LENGTH, CONTENT_LOCATION, CONTENT_DISPOSITION, CONTENT_MD5, CONTENT_TYPE, LAST_MODIFIED and LOCATION.	   * @param value the value to set.
	   */
	  public boolean isHeadersDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'outlinks' field.
	   * Embedded hyperlinks which direct outside of the current domain.	   */
	  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getOutlinks() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'outlinks' field.
	   * Embedded hyperlinks which direct outside of the current domain.	   * @param value the value to set.
	   */
	  public void setOutlinks(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'outlinks' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * Embedded hyperlinks which direct outside of the current domain.	   * @param value the value to set.
	   */
	  public boolean isOutlinksDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'inlinks' field.
	   * Embedded hyperlinks which link to pages within the current domain.	   */
	  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getInlinks() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'inlinks' field.
	   * Embedded hyperlinks which link to pages within the current domain.	   * @param value the value to set.
	   */
	  public void setInlinks(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'inlinks' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * Embedded hyperlinks which link to pages within the current domain.	   * @param value the value to set.
	   */
	  public boolean isInlinksDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'markers' field.
	   * Markers flags which represent user and machine decisions which have affected influenced a WebPage's current state. Markers can be system specific and user machine driven in nature. They are assigned to a WebPage on a job-by-job basis and thier values indicative of what actions should be associated with a WebPage.	   */
	  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getMarkers() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'markers' field.
	   * Markers flags which represent user and machine decisions which have affected influenced a WebPage's current state. Markers can be system specific and user machine driven in nature. They are assigned to a WebPage on a job-by-job basis and thier values indicative of what actions should be associated with a WebPage.	   * @param value the value to set.
	   */
	  public void setMarkers(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'markers' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * Markers flags which represent user and machine decisions which have affected influenced a WebPage's current state. Markers can be system specific and user machine driven in nature. They are assigned to a WebPage on a job-by-job basis and thier values indicative of what actions should be associated with a WebPage.	   * @param value the value to set.
	   */
	  public boolean isMarkersDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'metadata' field.
	   * A multi-valued metadata container used for storing everything from structured WebPage characterists, to ad-hoc extraction and metadata augmentation for any given WebPage.	   */
	  public java.util.Map<java.lang.CharSequence,java.nio.ByteBuffer> getMetadata() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'metadata' field.
	   * A multi-valued metadata container used for storing everything from structured WebPage characterists, to ad-hoc extraction and metadata augmentation for any given WebPage.	   * @param value the value to set.
	   */
	  public void setMetadata(java.util.Map<java.lang.CharSequence,java.nio.ByteBuffer> value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'metadata' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * A multi-valued metadata container used for storing everything from structured WebPage characterists, to ad-hoc extraction and metadata augmentation for any given WebPage.	   * @param value the value to set.
	   */
	  public boolean isMetadataDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'batchId' field.
	   * A batchId that this WebPage is assigned to. WebPage's are fetched in batches, called fetchlists. Pages are partitioned but can always be associated and fetched alongside pages of similar value (within a crawl cycle) based on batchId.	   */
	  public java.lang.CharSequence getBatchId() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'batchId' field.
	   * A batchId that this WebPage is assigned to. WebPage's are fetched in batches, called fetchlists. Pages are partitioned but can always be associated and fetched alongside pages of similar value (within a crawl cycle) based on batchId.	   * @param value the value to set.
	   */
	  public void setBatchId(java.lang.CharSequence value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'batchId' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * A batchId that this WebPage is assigned to. WebPage's are fetched in batches, called fetchlists. Pages are partitioned but can always be associated and fetched alongside pages of similar value (within a crawl cycle) based on batchId.	   * @param value the value to set.
	   */
	  public boolean isBatchIdDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'sitemaps' field.
	   * Sitemap urls in robot.txt	   */
	  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getSitemaps() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'sitemaps' field.
	   * Sitemap urls in robot.txt	   * @param value the value to set.
	   */
	  public void setSitemaps(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'sitemaps' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * Sitemap urls in robot.txt	   * @param value the value to set.
	   */
	  public boolean isSitemapsDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
				  /**
	   * Gets the value of the 'stmPriority' field.
	   * 	   */
	  public java.lang.Float getStmPriority() {
	    throw new java.lang.UnsupportedOperationException("Get is not supported on tombstones");
	  }
	
	  /**
	   * Sets the value of the 'stmPriority' field.
	   * 	   * @param value the value to set.
	   */
	  public void setStmPriority(java.lang.Float value) {
	    throw new java.lang.UnsupportedOperationException("Set is not supported on tombstones");
	  }
	  
	  /**
	   * Checks the dirty status of the 'stmPriority' field. A field is dirty if it represents a change that has not yet been written to the database.
	   * 	   * @param value the value to set.
	   */
	  public boolean isStmPriorityDirty() {
	    throw new java.lang.UnsupportedOperationException("IsDirty is not supported on tombstones");
	  }
	
		  
  }

  private static final org.apache.avro.io.DatumWriter
            DATUM_WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);
  private static final org.apache.avro.io.DatumReader
            DATUM_READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  /**
   * Writes AVRO data bean to output stream in the form of AVRO Binary encoding format. This will transform
   * AVRO data bean from its Java object form to it s serializable form.
   *
   * @param out java.io.ObjectOutput output stream to write data bean in serializable form
   */
  @Override
  public void writeExternal(java.io.ObjectOutput out)
          throws java.io.IOException {
    out.write(super.getDirtyBytes().array());
    DATUM_WRITER$.write(this, org.apache.avro.io.EncoderFactory.get()
            .directBinaryEncoder((java.io.OutputStream) out,
                    null));
  }

  /**
   * Reads AVRO data bean from input stream in it s AVRO Binary encoding format to Java object format.
   * This will transform AVRO data bean from it s serializable form to deserialized Java object form.
   *
   * @param in java.io.ObjectOutput input stream to read data bean in serializable form
   */
  @Override
  public void readExternal(java.io.ObjectInput in)
          throws java.io.IOException {
    byte[] __g__dirty = new byte[getFieldsCount()];
    in.read(__g__dirty);
    super.setDirtyBytes(java.nio.ByteBuffer.wrap(__g__dirty));
    DATUM_READER$.read(this, org.apache.avro.io.DecoderFactory.get()
            .directBinaryDecoder((java.io.InputStream) in,
                    null));
  }
  
}


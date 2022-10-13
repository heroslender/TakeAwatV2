package com.heroslender.takeawat.retrofit.result

@Suppress("unused")
enum class HttpStatus(
    /**
     * The HTTP status code integer value.
     */
    val value: Int,

    /**
     * The HTTP status [Series] of this status code.
     *
     * @see HttpStatus.Series
     */
    val series: Series,

    /**
     * The reason phrase of this status code.
     */
    val reasonPhrase: String
) {
    // 1xx Informational
    /**
     * `100 Continue`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.2.1">HTTP/1.1: Semantics and Content, section 6.2.1</a>
     */
    CONTINUE(100, Series.INFORMATIONAL, "Continue"),

    /**
     * `101 Switching Protocols`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.2.2">HTTP/1.1: Semantics and Content, section 6.2.2</a>
     */
    SWITCHING_PROTOCOLS(101, Series.INFORMATIONAL, "Switching Protocols"),

    /**
     * `102 Processing`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc2518.section-10.1">WebDAV</a>
     */
    PROCESSING(102, Series.INFORMATIONAL, "Processing"),

    /**
     * `103 Checkpoint`.
     *
     * @see <a href="https://code.google.com/p/gears/wiki/ResumableHttpRequestsProposal">A proposal for supporting resumable POST/PUT HTTP requests in HTTP/1.0</a>
     */
    CHECKPOINT(103, Series.INFORMATIONAL, "Checkpoint"),  // 2xx Success

    /**
     * `200 OK`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.3.1">HTTP/1.1: Semantics and Content, section 6.3.1</a>
     */
    OK(200, Series.SUCCESSFUL, "OK"),

    /**
     * `201 Created`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.3.2">HTTP/1.1: Semantics and Content, section 6.3.2</a>
     */
    CREATED(201, Series.SUCCESSFUL, "Created"),

    /**
     * `202 Accepted`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.3.3">HTTP/1.1: Semantics and Content, section 6.3.3</a>
     */
    ACCEPTED(202, Series.SUCCESSFUL, "Accepted"),

    /**
     * `203 Non-Authoritative Information`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.3.4">HTTP/1.1: Semantics and Content, section 6.3.4</a>
     */
    NON_AUTHORITATIVE_INFORMATION(203, Series.SUCCESSFUL, "Non-Authoritative Information"),

    /**
     * `204 No Content`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.3.5">HTTP/1.1: Semantics and Content, section 6.3.5</a>
     */
    NO_CONTENT(204, Series.SUCCESSFUL, "No Content"),

    /**
     * `205 Reset Content`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.3.6">HTTP/1.1: Semantics and Content, section 6.3.6</a>
     */
    RESET_CONTENT(205, Series.SUCCESSFUL, "Reset Content"),

    /**
     * `206 Partial Content`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7233.section-4.1">HTTP/1.1: Range Requests, section 4.1</a>
     */
    PARTIAL_CONTENT(206, Series.SUCCESSFUL, "Partial Content"),

    /**
     * `207 Multi-Status`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc4918.section-13">WebDAV</a>
     */
    MULTI_STATUS(207, Series.SUCCESSFUL, "Multi-Status"),

    /**
     * `208 Already Reported`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc5842.section-7.1">WebDAV Binding Extensions</a>
     */
    ALREADY_REPORTED(208, Series.SUCCESSFUL, "Already Reported"),

    /**
     * `226 IM Used`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc3229.section-10.4.1">Delta encoding in HTTP</a>
     */
    IM_USED(226, Series.SUCCESSFUL, "IM Used"),  // 3xx Redirection

    /**
     * `300 Multiple Choices`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.4.1">HTTP/1.1: Semantics and Content, section 6.4.1</a>
     */
    MULTIPLE_CHOICES(300, Series.REDIRECTION, "Multiple Choices"),

    /**
     * `301 Moved Permanently`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.4.2">HTTP/1.1: Semantics and Content, section 6.4.2</a>
     */
    MOVED_PERMANENTLY(301, Series.REDIRECTION, "Moved Permanently"),

    /**
     * `302 Moved Temporarily`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc1945.section-9.3">HTTP/1.0, section 9.3</a>
     */
    MOVED_TEMPORARILY(302, Series.REDIRECTION, "Moved Temporarily"),

    /**
     * `303 See Other`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.4.4">HTTP/1.1: Semantics and Content, section 6.4.4</a>
     */
    SEE_OTHER(303, Series.REDIRECTION, "See Other"),

    /**
     * `304 Not Modified`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7232.section-4.1">HTTP/1.1: Conditional Requests, section 4.1</a>
     */
    NOT_MODIFIED(304, Series.REDIRECTION, "Not Modified"),

    /**
     * `305 Use Proxy`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.4.5">HTTP/1.1: Semantics and Content, section 6.4.5</a>
     */
    USE_PROXY(305, Series.REDIRECTION, "Use Proxy"),

    /**
     * `307 Temporary Redirect`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.4.7">HTTP/1.1: Semantics and Content, section 6.4.7</a>
     */
    TEMPORARY_REDIRECT(307, Series.REDIRECTION, "Temporary Redirect"),

    /**
     * `308 Permanent Redirect`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7238">RFC 7238</a>
     */
    PERMANENT_REDIRECT(308, Series.REDIRECTION, "Permanent Redirect"),  // --- 4xx Client Error ---

    /**
     * `400 Bad Request`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.1">HTTP/1.1: Semantics and Content, section 6.5.1</a>
     */
    BAD_REQUEST(400, Series.CLIENT_ERROR, "Bad Request"),

    /**
     * `401 Unauthorized`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7235.section-3.1">HTTP/1.1: Authentication, section 3.1</a>
     */
    UNAUTHORIZED(401, Series.CLIENT_ERROR, "Unauthorized"),

    /**
     * `402 Payment Required`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.2">HTTP/1.1: Semantics and Content, section 6.5.2</a>
     */
    PAYMENT_REQUIRED(402, Series.CLIENT_ERROR, "Payment Required"),

    /**
     * `403 Forbidden`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.3">HTTP/1.1: Semantics and Content, section 6.5.3</a>
     */
    FORBIDDEN(403, Series.CLIENT_ERROR, "Forbidden"),

    /**
     * `404 Not Found`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.4">HTTP/1.1: Semantics and Content, section 6.5.4</a>
     */
    NOT_FOUND(404, Series.CLIENT_ERROR, "Not Found"),

    /**
     * `405 Method Not Allowed`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.5">HTTP/1.1: Semantics and Content, section 6.5.5</a>
     */
    METHOD_NOT_ALLOWED(405, Series.CLIENT_ERROR, "Method Not Allowed"),

    /**
     * `406 Not Acceptable`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.6">HTTP/1.1: Semantics and Content, section 6.5.6</a>
     */
    NOT_ACCEPTABLE(406, Series.CLIENT_ERROR, "Not Acceptable"),

    /**
     * `407 Proxy Authentication Required`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7235.section-3.2">HTTP/1.1: Authentication, section 3.2</a>
     */
    PROXY_AUTHENTICATION_REQUIRED(407, Series.CLIENT_ERROR, "Proxy Authentication Required"),

    /**
     * `408 Request Timeout`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.7">HTTP/1.1: Semantics and Content, section 6.5.7</a>
     */
    REQUEST_TIMEOUT(408, Series.CLIENT_ERROR, "Request Timeout"),

    /**
     * `409 Conflict`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.8">HTTP/1.1: Semantics and Content, section 6.5.8</a>
     */
    CONFLICT(409, Series.CLIENT_ERROR, "Conflict"),

    /**
     * `410 Gone`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.9">HTTP/1.1: Semantics and Content, section 6.5.9</a>
     */
    GONE(410, Series.CLIENT_ERROR, "Gone"),

    /**
     * `411 Length Required`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.10">HTTP/1.1: Semantics and Content, section 6.5.10</a>
     */
    LENGTH_REQUIRED(411, Series.CLIENT_ERROR, "Length Required"),

    /**
     * `412 Precondition failed`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7232.section-4.2">HTTP/1.1: Conditional Requests, section 4.2</a>
     */
    PRECONDITION_FAILED(412, Series.CLIENT_ERROR, "Precondition Failed"),

    /**
     * `413 Payload Too Large`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.11">HTTP/1.1: Semantics and Content, section 6.5.11</a>
     *
     */
    PAYLOAD_TOO_LARGE(413, Series.CLIENT_ERROR, "Payload Too Large"),

    /**
     * `414 URI Too Long`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.12">HTTP/1.1: Semantics and Content, section 6.5.12</a>
     */
    URI_TOO_LONG(414, Series.CLIENT_ERROR, "URI Too Long"),

    /**
     * `415 Unsupported Media Type`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.13">HTTP/1.1: Semantics and Content, section 6.5.13</a>
     */
    UNSUPPORTED_MEDIA_TYPE(415, Series.CLIENT_ERROR, "Unsupported Media Type"),

    /**
     * `416 Requested Range Not Satisfiable`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7233.section-4.4">HTTP/1.1: Range Requests, section 4.4</a>
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(416, Series.CLIENT_ERROR, "Requested range not satisfiable"),

    /**
     * `417 Expectation Failed`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.5.14">HTTP/1.1: Semantics and Content, section 6.5.14</a>
     */
    EXPECTATION_FAILED(417, Series.CLIENT_ERROR, "Expectation Failed"),

    /**
     * `418 I'm a teapot`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc2324.section-2.3.2">HTCPCP/1.0</a>
     */
    I_AM_A_TEAPOT(418, Series.CLIENT_ERROR, "I'm a teapot"),

    /**
     * `422 Unprocessable Entity`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc4918#section-11.2">WebDAV</a>
     */
    UNPROCESSABLE_ENTITY(422, Series.CLIENT_ERROR, "Unprocessable Entity"),

    /**
     * `423 Locked`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc4918.section-11.3">WebDAV</a>
     */
    LOCKED(423, Series.CLIENT_ERROR, "Locked"),

    /**
     * `424 Failed Dependency`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc4918.section-11.4">WebDAV</a>
     */
    FAILED_DEPENDENCY(424, Series.CLIENT_ERROR, "Failed Dependency"),

    /**
     * `425 Too Early`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc8470">RFC 8470</a>
     */
    TOO_EARLY(425, Series.CLIENT_ERROR, "Too Early"),

    /**
     * `426 Upgrade Required`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc2817.section-6">Upgrading to TLS Within HTTP/1.1</a>
     */
    UPGRADE_REQUIRED(426, Series.CLIENT_ERROR, "Upgrade Required"),

    /**
     * `428 Precondition Required`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6585.section-3">Additional HTTP Status Codes</a>
     */
    PRECONDITION_REQUIRED(428, Series.CLIENT_ERROR, "Precondition Required"),

    /**
     * `429 Too Many Requests`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6585.section-4">Additional HTTP Status Codes</a>
     */
    TOO_MANY_REQUESTS(429, Series.CLIENT_ERROR, "Too Many Requests"),

    /**
     * `431 Request Header Fields Too Large`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6585.section-5">Additional HTTP Status Codes</a>
     */
    REQUEST_HEADER_FIELDS_TOO_LARGE(
        431,
        Series.CLIENT_ERROR,
        "Request Header Fields Too Large"
    ),

    /**
     * `451 Unavailable For Legal Reasons`.
     *
     * @see <a href="https://tools.ietf.org/html/draft-ietf-httpbis-legally-restricted-status-04">An HTTP Status Code to Report Legal Obstacles</a>
     */
    UNAVAILABLE_FOR_LEGAL_REASONS(
        451,
        Series.CLIENT_ERROR,
        "Unavailable For Legal Reasons"
    ),  // --- 5xx Server Error ---

    /**
     * `500 Internal Server Error`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.6.1">HTTP/1.1: Semantics and Content, section 6.6.1</a>
     */
    INTERNAL_SERVER_ERROR(500, Series.SERVER_ERROR, "Internal Server Error"),

    /**
     * `501 Not Implemented`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.6.2">HTTP/1.1: Semantics and Content, section 6.6.2</a>
     */
    NOT_IMPLEMENTED(501, Series.SERVER_ERROR, "Not Implemented"),

    /**
     * `502 Bad Gateway`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.6.3">HTTP/1.1: Semantics and Content, section 6.6.3</a>
     */
    BAD_GATEWAY(502, Series.SERVER_ERROR, "Bad Gateway"),

    /**
     * `503 Service Unavailable`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.6.4">HTTP/1.1: Semantics and Content, section 6.6.4</a>
     */
    SERVICE_UNAVAILABLE(503, Series.SERVER_ERROR, "Service Unavailable"),

    /**
     * `504 Gateway Timeout`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.6.5">HTTP/1.1: Semantics and Content, section 6.6.5</a>
     */
    GATEWAY_TIMEOUT(504, Series.SERVER_ERROR, "Gateway Timeout"),

    /**
     * `505 HTTP Version Not Supported`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7231.section-6.6.6">HTTP/1.1: Semantics and Content, section 6.6.6</a>
     */
    HTTP_VERSION_NOT_SUPPORTED(505, Series.SERVER_ERROR, "HTTP Version not supported"),

    /**
     * `506 Variant Also Negotiates`
     *
     * @see <a href="https://tools.ietf.org/html/rfc2295.section-8.1">Transparent Content Negotiation</a>
     */
    VARIANT_ALSO_NEGOTIATES(506, Series.SERVER_ERROR, "Variant Also Negotiates"),

    /**
     * `507 Insufficient Storage`
     *
     * @see <a href="https://tools.ietf.org/html/rfc4918.section-11.5">WebDAV</a>
     */
    INSUFFICIENT_STORAGE(507, Series.SERVER_ERROR, "Insufficient Storage"),

    /**
     * `508 Loop Detected`
     *
     * @see <a href="https://tools.ietf.org/html/rfc5842.section-7.2">WebDAV Binding Extensions</a>
     */
    LOOP_DETECTED(508, Series.SERVER_ERROR, "Loop Detected"),

    /**
     * `509 Bandwidth Limit Exceeded`
     */
    BANDWIDTH_LIMIT_EXCEEDED(509, Series.SERVER_ERROR, "Bandwidth Limit Exceeded"),

    /**
     * `510 Not Extended`
     *
     * @see <a href="https://tools.ietf.org/html/rfc2774.section-7">HTTP Extension Framework</a>
     */
    NOT_EXTENDED(510, Series.SERVER_ERROR, "Not Extended"),

    /**
     * `511 Network Authentication Required`.
     *
     * @see <a href="https://tools.ietf.org/html/rfc6585.section-6">Additional HTTP Status Codes</a>
     */
    NETWORK_AUTHENTICATION_REQUIRED(511, Series.SERVER_ERROR, "Network Authentication Required");

    fun is1xxInformational(): Boolean {
        return series == Series.INFORMATIONAL
    }

    fun is2xxSuccessful(): Boolean {
        return series == Series.SUCCESSFUL
    }

    fun is3xxRedirection(): Boolean {
        return series == Series.REDIRECTION
    }

    fun is4xxClientError(): Boolean {
        return series == Series.CLIENT_ERROR
    }

    fun is5xxServerError(): Boolean {
        return series == Series.SERVER_ERROR
    }

    val isError: Boolean
        get() = is4xxClientError() || is5xxServerError()

    /**
     * Return a string representation of this status code.
     */
    override fun toString(): String {
        return "$value $name"
    }

    /**
     * Enumeration of HTTP status series.
     *
     * Retrievable via [series].
     */
    enum class Series(
        /**
         * The integer value of this status series. Ranges from 1 to 5.
         */
        val value: Int
    ) {
        INFORMATIONAL(1),
        SUCCESSFUL(2),
        REDIRECTION(3),
        CLIENT_ERROR(4),
        SERVER_ERROR(5);
    }

    companion object {
        private val VALUES: Array<HttpStatus> = values()

        /**
         * Return the `HttpStatus` enum constant with the specified numeric value.
         *
         * @param statusCode the numeric value of the enum to be returned
         * @return the enum constant with the specified numeric value
         * @throws IllegalArgumentException if this enum has no constant for the specified numeric value
         */
        fun valueOf(statusCode: Int): HttpStatus {
            return resolve(statusCode)
                ?: throw IllegalArgumentException("No matching constant for [$statusCode]")
        }

        /**
         * Resolve the given status code to an `HttpStatus`, if possible.
         *
         * @param statusCode the HTTP status code (potentially non-standard)
         * @return the corresponding `HttpStatus`, or `null` if not found
         */
        @Suppress("MemberVisibilityCanBePrivate")
        fun resolve(statusCode: Int): HttpStatus? {
            // Use cached VALUES instead of values() to prevent array allocation.
            for (status in VALUES) {
                if (status.value == statusCode) {
                    return status
                }
            }
            return null
        }
    }
}
/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.dataproc.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.dataproc.v1.AutoscalingPolicyServiceClient;
import com.google.cloud.dataproc.v1.AutoscalingPolicyServiceSettings;
import com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration;
import com.google.cloud.spring.core.DefaultCredentialsProvider;
import com.google.cloud.spring.core.Retry;
import com.google.cloud.spring.core.util.RetryUtil;
import java.io.IOException;
import java.util.Collections;
import javax.annotation.Generated;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/**
 * Auto-configuration for {@link AutoscalingPolicyServiceClient}.
 *
 * <p>Provides auto-configuration for Spring Boot
 *
 * <p>The default instance has everything set to sensible defaults:
 *
 * <ul>
 *   <li>The default transport provider is used.
 *   <li>Credentials are acquired automatically through Application Default Credentials.
 *   <li>Retries are configured for idempotent methods but not for non-idempotent methods.
 * </ul>
 */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@AutoConfiguration
@AutoConfigureAfter(GcpContextAutoConfiguration.class)
@ConditionalOnClass(AutoscalingPolicyServiceClient.class)
@ConditionalOnProperty(
    value = "com.google.cloud.dataproc.v1.autoscaling-policy-service.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(AutoscalingPolicyServiceSpringProperties.class)
public class AutoscalingPolicyServiceSpringAutoConfiguration {
  private final AutoscalingPolicyServiceSpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER =
      LogFactory.getLog(AutoscalingPolicyServiceSpringAutoConfiguration.class);

  protected AutoscalingPolicyServiceSpringAutoConfiguration(
      AutoscalingPolicyServiceSpringProperties clientProperties,
      CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from AutoscalingPolicyService-specific configuration");
      }
      this.credentialsProvider =
          ((CredentialsProvider) new DefaultCredentialsProvider(this.clientProperties));
    } else {
      this.credentialsProvider = credentialsProvider;
    }
  }

  /**
   * Provides a default transport channel provider bean, corresponding to the client library's
   * default transport channel provider. If the library supports both GRPC and REST transport, and
   * the useRest property is configured, the HTTP/JSON transport provider will be used instead of
   * GRPC.
   *
   * @return a default transport channel provider.
   */
  @Bean
  @ConditionalOnMissingBean(name = "defaultAutoscalingPolicyServiceTransportChannelProvider")
  public TransportChannelProvider defaultAutoscalingPolicyServiceTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return AutoscalingPolicyServiceSettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return AutoscalingPolicyServiceSettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a AutoscalingPolicyServiceSettings bean configured to use a DefaultCredentialsProvider
   * and the client library's default transport channel provider
   * (defaultAutoscalingPolicyServiceTransportChannelProvider()). It also configures the quota
   * project ID and executor thread count, if provided through properties.
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in AutoscalingPolicyServiceSpringProperties. Method-level properties will take precedence over
   * service-level properties if available, and client library defaults will be used if neither are
   * specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link AutoscalingPolicyServiceSettings} bean configured with {@link
   *     TransportChannelProvider} bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public AutoscalingPolicyServiceSettings autoscalingPolicyServiceSettings(
      @Qualifier("defaultAutoscalingPolicyServiceTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    AutoscalingPolicyServiceSettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = AutoscalingPolicyServiceSettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = AutoscalingPolicyServiceSettings.newBuilder();
    }
    clientSettingsBuilder
        .setCredentialsProvider(this.credentialsProvider)
        .setTransportChannelProvider(defaultTransportChannelProvider)
        .setHeaderProvider(this.userAgentHeaderProvider());
    if (this.clientProperties.getQuotaProjectId() != null) {
      clientSettingsBuilder.setQuotaProjectId(this.clientProperties.getQuotaProjectId());
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Quota project id set to "
                + this.clientProperties.getQuotaProjectId()
                + ", this overrides project id from credentials.");
      }
    }
    if (this.clientProperties.getExecutorThreadCount() != null) {
      ExecutorProvider executorProvider =
          AutoscalingPolicyServiceSettings.defaultExecutorProviderBuilder()
              .setExecutorThreadCount(this.clientProperties.getExecutorThreadCount())
              .build();
      clientSettingsBuilder.setBackgroundExecutorProvider(executorProvider);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Background executor thread count is "
                + this.clientProperties.getExecutorThreadCount());
      }
    }
    Retry serviceRetry = clientProperties.getRetry();
    if (serviceRetry != null) {
      RetrySettings createAutoscalingPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createAutoscalingPolicySettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .createAutoscalingPolicySettings()
          .setRetrySettings(createAutoscalingPolicyRetrySettings);

      RetrySettings updateAutoscalingPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateAutoscalingPolicySettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .updateAutoscalingPolicySettings()
          .setRetrySettings(updateAutoscalingPolicyRetrySettings);

      RetrySettings getAutoscalingPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getAutoscalingPolicySettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .getAutoscalingPolicySettings()
          .setRetrySettings(getAutoscalingPolicyRetrySettings);

      RetrySettings listAutoscalingPoliciesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listAutoscalingPoliciesSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .listAutoscalingPoliciesSettings()
          .setRetrySettings(listAutoscalingPoliciesRetrySettings);

      RetrySettings deleteAutoscalingPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteAutoscalingPolicySettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .deleteAutoscalingPolicySettings()
          .setRetrySettings(deleteAutoscalingPolicyRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry createAutoscalingPolicyRetry = clientProperties.getCreateAutoscalingPolicyRetry();
    if (createAutoscalingPolicyRetry != null) {
      RetrySettings createAutoscalingPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createAutoscalingPolicySettings().getRetrySettings(),
              createAutoscalingPolicyRetry);
      clientSettingsBuilder
          .createAutoscalingPolicySettings()
          .setRetrySettings(createAutoscalingPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for createAutoscalingPolicy from properties.");
      }
    }
    Retry updateAutoscalingPolicyRetry = clientProperties.getUpdateAutoscalingPolicyRetry();
    if (updateAutoscalingPolicyRetry != null) {
      RetrySettings updateAutoscalingPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateAutoscalingPolicySettings().getRetrySettings(),
              updateAutoscalingPolicyRetry);
      clientSettingsBuilder
          .updateAutoscalingPolicySettings()
          .setRetrySettings(updateAutoscalingPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for updateAutoscalingPolicy from properties.");
      }
    }
    Retry getAutoscalingPolicyRetry = clientProperties.getGetAutoscalingPolicyRetry();
    if (getAutoscalingPolicyRetry != null) {
      RetrySettings getAutoscalingPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getAutoscalingPolicySettings().getRetrySettings(),
              getAutoscalingPolicyRetry);
      clientSettingsBuilder
          .getAutoscalingPolicySettings()
          .setRetrySettings(getAutoscalingPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for getAutoscalingPolicy from properties.");
      }
    }
    Retry listAutoscalingPoliciesRetry = clientProperties.getListAutoscalingPoliciesRetry();
    if (listAutoscalingPoliciesRetry != null) {
      RetrySettings listAutoscalingPoliciesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listAutoscalingPoliciesSettings().getRetrySettings(),
              listAutoscalingPoliciesRetry);
      clientSettingsBuilder
          .listAutoscalingPoliciesSettings()
          .setRetrySettings(listAutoscalingPoliciesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listAutoscalingPolicies from properties.");
      }
    }
    Retry deleteAutoscalingPolicyRetry = clientProperties.getDeleteAutoscalingPolicyRetry();
    if (deleteAutoscalingPolicyRetry != null) {
      RetrySettings deleteAutoscalingPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteAutoscalingPolicySettings().getRetrySettings(),
              deleteAutoscalingPolicyRetry);
      clientSettingsBuilder
          .deleteAutoscalingPolicySettings()
          .setRetrySettings(deleteAutoscalingPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for deleteAutoscalingPolicy from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a AutoscalingPolicyServiceClient bean configured with
   * AutoscalingPolicyServiceSettings.
   *
   * @param autoscalingPolicyServiceSettings settings to configure an instance of client bean.
   * @return a {@link AutoscalingPolicyServiceClient} bean configured with {@link
   *     AutoscalingPolicyServiceSettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public AutoscalingPolicyServiceClient autoscalingPolicyServiceClient(
      AutoscalingPolicyServiceSettings autoscalingPolicyServiceSettings) throws IOException {
    return AutoscalingPolicyServiceClient.create(autoscalingPolicyServiceSettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-autoscaling-policy-service";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}

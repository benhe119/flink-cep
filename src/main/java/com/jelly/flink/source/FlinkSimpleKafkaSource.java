package com.jelly.flink.source;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.io.Serializable;
import java.util.Properties;

/**
 * kafka-source
 * <p>
 *
 * @author jelly.wang
 * @create 2019/03/26
 */
public class FlinkSimpleKafkaSource implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bootstrapServers;
    private String groupId;
    private String topic;

    public FlinkSimpleKafkaSource(String bootstrapServers, String groupId, String topic) {
        this.bootstrapServers = bootstrapServers;
        this.groupId = groupId;
        this.topic = topic;
    }

    public FlinkKafkaConsumer build() {
        final Properties properties = new Properties() {{
            setProperty("bootstrap.servers", bootstrapServers);
            setProperty("group.id", groupId);
            setProperty("auto.offset.reset", "earliest");
            setProperty("enable.auto.commit", "true");
            setProperty("auto.commit.interval.ms", "1000");
            setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        }};
        return new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), properties);
    }

}

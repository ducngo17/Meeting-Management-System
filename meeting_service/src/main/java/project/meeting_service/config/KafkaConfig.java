package project.meeting_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic createTopic() {
        // Placeholder, topics will be created dynamically
        return null;
    }
    @Bean
    public NewTopic meeting1Topic() {
        return new NewTopic("meeting-1", 1, (short) 1);
    }

    @Bean
    public NewTopic meeting2Topic() {
        return new NewTopic("meeting-2", 1, (short) 1);
    }

    @Bean
    public NewTopic meeting3Topic() {
        return new NewTopic("meeting-3", 1, (short) 1);
    }

    @Bean
    public NewTopic meeting4Topic() {
        return new NewTopic("meeting-4", 1, (short) 1);
    }

    @Bean
    public NewTopic meeting5Topic() {
        return new NewTopic("meeting-5", 1, (short) 1);
    }
}
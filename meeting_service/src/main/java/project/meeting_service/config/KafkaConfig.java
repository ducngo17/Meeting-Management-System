package project.meeting_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic createTopic() {
        // Placeholder, topics will be created dynamically
        return null;
    }
//    @Bean
//    public List<NewTopic> createMeetingTopics() {
//        List<NewTopic> topics = new ArrayList<>();
//        for (int i = 1; i <= 50; i++) {
//            topics.add(
//                    TopicBuilder.name("meeting-" + i)
//                            .partitions(1)
//                            .replicas(1)
//                            .build()
//            );
//        }
//        return topics;
//    }

    // Beans for meeting-1 to meeting-50
    @Bean public NewTopic meeting1Topic() { return new NewTopic("meeting-1", 1, (short) 1); }
    @Bean public NewTopic meeting2Topic() { return new NewTopic("meeting-2", 1, (short) 1); }
    @Bean public NewTopic meeting3Topic() { return new NewTopic("meeting-3", 1, (short) 1); }
    @Bean public NewTopic meeting4Topic() { return new NewTopic("meeting-4", 1, (short) 1); }
    @Bean public NewTopic meeting5Topic() { return new NewTopic("meeting-5", 1, (short) 1); }
    @Bean public NewTopic meeting6Topic() { return new NewTopic("meeting-6", 1, (short) 1); }
    @Bean public NewTopic meeting7Topic() { return new NewTopic("meeting-7", 1, (short) 1); }
    @Bean public NewTopic meeting8Topic() { return new NewTopic("meeting-8", 1, (short) 1); }
    @Bean public NewTopic meeting9Topic() { return new NewTopic("meeting-9", 1, (short) 1); }
    @Bean public NewTopic meeting10Topic() { return new NewTopic("meeting-10", 1, (short) 1); }
    @Bean public NewTopic meeting11Topic() { return new NewTopic("meeting-11", 1, (short) 1); }
    @Bean public NewTopic meeting12Topic() { return new NewTopic("meeting-12", 1, (short) 1); }
    @Bean public NewTopic meeting13Topic() { return new NewTopic("meeting-13", 1, (short) 1); }
    @Bean public NewTopic meeting14Topic() { return new NewTopic("meeting-14", 1, (short) 1); }
    @Bean public NewTopic meeting15Topic() { return new NewTopic("meeting-15", 1, (short) 1); }
    @Bean public NewTopic meeting16Topic() { return new NewTopic("meeting-16", 1, (short) 1); }
    @Bean public NewTopic meeting17Topic() { return new NewTopic("meeting-17", 1, (short) 1); }
    @Bean public NewTopic meeting18Topic() { return new NewTopic("meeting-18", 1, (short) 1); }
    @Bean public NewTopic meeting19Topic() { return new NewTopic("meeting-19", 1, (short) 1); }
    @Bean public NewTopic meeting20Topic() { return new NewTopic("meeting-20", 1, (short) 1); }
    @Bean public NewTopic meeting21Topic() { return new NewTopic("meeting-21", 1, (short) 1); }
    @Bean public NewTopic meeting22Topic() { return new NewTopic("meeting-22", 1, (short) 1); }
    @Bean public NewTopic meeting23Topic() { return new NewTopic("meeting-23", 1, (short) 1); }
    @Bean public NewTopic meeting24Topic() { return new NewTopic("meeting-24", 1, (short) 1); }
    @Bean public NewTopic meeting25Topic() { return new NewTopic("meeting-25", 1, (short) 1); }
    @Bean public NewTopic meeting26Topic() { return new NewTopic("meeting-26", 1, (short) 1); }
    @Bean public NewTopic meeting27Topic() { return new NewTopic("meeting-27", 1, (short) 1); }
    @Bean public NewTopic meeting28Topic() { return new NewTopic("meeting-28", 1, (short) 1); }
    @Bean public NewTopic meeting29Topic() { return new NewTopic("meeting-29", 1, (short) 1); }
    @Bean public NewTopic meeting30Topic() { return new NewTopic("meeting-30", 1, (short) 1); }
    @Bean public NewTopic meeting31Topic() { return new NewTopic("meeting-31", 1, (short) 1); }
    @Bean public NewTopic meeting32Topic() { return new NewTopic("meeting-32", 1, (short) 1); }
    @Bean public NewTopic meeting33Topic() { return new NewTopic("meeting-33", 1, (short) 1); }
    @Bean public NewTopic meeting34Topic() { return new NewTopic("meeting-34", 1, (short) 1); }
    @Bean public NewTopic meeting35Topic() { return new NewTopic("meeting-35", 1, (short) 1); }
    @Bean public NewTopic meeting36Topic() { return new NewTopic("meeting-36", 1, (short) 1); }
    @Bean public NewTopic meeting37Topic() { return new NewTopic("meeting-37", 1, (short) 1); }
    @Bean public NewTopic meeting38Topic() { return new NewTopic("meeting-38", 1, (short) 1); }
    @Bean public NewTopic meeting39Topic() { return new NewTopic("meeting-39", 1, (short) 1); }
    @Bean public NewTopic meeting40Topic() { return new NewTopic("meeting-40", 1, (short) 1); }
    @Bean public NewTopic meeting41Topic() { return new NewTopic("meeting-41", 1, (short) 1); }
    @Bean public NewTopic meeting42Topic() { return new NewTopic("meeting-42", 1, (short) 1); }
    @Bean public NewTopic meeting43Topic() { return new NewTopic("meeting-43", 1, (short) 1); }
    @Bean public NewTopic meeting44Topic() { return new NewTopic("meeting-44", 1, (short) 1); }
    @Bean public NewTopic meeting45Topic() { return new NewTopic("meeting-45", 1, (short) 1); }
    @Bean public NewTopic meeting46Topic() { return new NewTopic("meeting-46", 1, (short) 1); }
    @Bean public NewTopic meeting47Topic() { return new NewTopic("meeting-47", 1, (short) 1); }
    @Bean public NewTopic meeting48Topic() { return new NewTopic("meeting-48", 1, (short) 1); }
    @Bean public NewTopic meeting49Topic() { return new NewTopic("meeting-49", 1, (short) 1); }
    @Bean public NewTopic meeting50Topic() { return new NewTopic("meeting-50", 1, (short) 1); }
}

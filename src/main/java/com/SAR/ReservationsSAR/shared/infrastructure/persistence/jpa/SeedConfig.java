package com.SAR.ReservationsSAR.shared.infrastructure.persistence.jpa;

import com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.entities.EstablishmentEntity;
import com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.entities.EstablishmentImageEntity;
import com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.repository.JpaEstablishmentImageRepository;
import com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.repository.JpaEstablishmentRepository;
import com.SAR.ReservationsSAR.context.topic.infrastructure.persistence.jpa.entities.TopicEntity;
import com.SAR.ReservationsSAR.context.topic.infrastructure.persistence.jpa.repository.JpaTopicRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SeedConfig {

    private final JpaTopicRepository topicRepository;
    private final JpaEstablishmentRepository establishmentRepository;
    private final JpaEstablishmentImageRepository establishmentImageRepository;

    @Bean
    @Transactional
    public CommandLineRunner generateSeed() {
        return args -> {
            var multimediaProjection = this.topicRepository.save(TopicEntity.builder()
                    .name("Proyección multimedia / ecran").build());

            var conference = this.topicRepository.save(TopicEntity.builder()
                    .name("Conferencia").build());

            var ceremony = this.topicRepository.save(TopicEntity.builder()
                    .name("Ceremonias").build());

            var workshop = this.topicRepository.save(TopicEntity.builder()
                    .name("Taller").build());

            var school = this.topicRepository.save(TopicEntity.builder()
                    .name("Escuela").build());

            var lunch = this.topicRepository.save(TopicEntity.builder()
                    .name("Almuerzo").build());

            var dinner = this.topicRepository.save(TopicEntity.builder()
                    .name("Cena").build());

            var productPresentation = this.topicRepository.save(TopicEntity.builder()
                    .name("Presentación de producto").build());

            var marriage = this.topicRepository.save(TopicEntity.builder()
                    .name("Matrimonio").build());

            var anniversary = this.topicRepository.save(TopicEntity.builder()
                    .name("Aniversario").build());

            var party = this.topicRepository.save(TopicEntity.builder()
                    .name("Fiesta").build());

            var dancingLunch = this.topicRepository.save(TopicEntity.builder()
                    .name("Almuerzo bailable").build());

            var institutionalEvent = this.topicRepository.save(TopicEntity.builder()
                    .name("Evento institucional").build());

            var picnicLunch = this.topicRepository.save(TopicEntity.builder()
                    .name("Almuerzo campestre").build());

            var competitiveGame = this.topicRepository.save(TopicEntity.builder()
                    .name("Juego de competencia").build());

            var sportsChampionship = this.topicRepository.save(TopicEntity.builder()
                    .name("Campeonato deportivo").build());

            var teamBuildingDynamic = this.topicRepository.save(TopicEntity.builder()
                    .name("Dinámicas de \"team building\"").build());

            var sportsEvent = this.topicRepository.save(TopicEntity.builder()
                    .name("Evento deportivo").build());

            var endOfYearEvent = this.topicRepository.save(TopicEntity.builder()
                    .name("Evento de fin de año").build());

            var pachamancas = this.topicRepository.save(TopicEntity.builder()
                    .name("pachamanca").build());

            var simultaneousActivities = this.topicRepository.save(TopicEntity.builder()
                    .name("Actividades en simultaneo").build());

            var establishments = List.of(
                    EstablishmentEntity.builder()
                            .name("Sala de directorio")
                            .description("Descripción 1")
                            .pricePerHour(1500)
                            .maxCapacity(12)
                            .topics(List.of(multimediaProjection))
                            .build(),
                    EstablishmentEntity.builder()
                            .name("Sala el fundador")
                            .description("Descripción 2")
                            .pricePerHour(2000)
                            .maxCapacity(80)
                            .topics(List.of(multimediaProjection, conference, workshop, school, ceremony))
                            .build(),
                    EstablishmentEntity.builder()
                            .name("Sala de piedra")
                            .description("Descripción 3")
                            .pricePerHour(2000)
                            .maxCapacity(120)
                            .topics(List.of(conference, lunch, productPresentation, workshop))
                            .build(),
                    EstablishmentEntity.builder()
                            .name("Restaurante el mesón")
                            .description("Descripción 4")
                            .pricePerHour(2000)
                            .maxCapacity(200)
                            .topics(List.of(lunch, dinner, marriage, anniversary))
                            .build(),
                    EstablishmentEntity.builder()
                            .name("Anfiteatro")
                            .description("Descripción 5")
                            .pricePerHour(2000)
                            .maxCapacity(600)
                            .topics(List.of(party, dancingLunch, anniversary, institutionalEvent))
                            .build(),
                    EstablishmentEntity.builder()
                            .name("Explanada baja de piscina")
                            .description("Descripción 6")
                            .pricePerHour(2000)
                            .maxCapacity(800)
                            .topics(List.of(
                                    picnicLunch,
                                    teamBuildingDynamic,
                                    party,
                                    dancingLunch,
                                    anniversary,
                                    competitiveGame
                            )).build(),
                    EstablishmentEntity.builder()
                            .name("Explanada de piscina alta")
                            .description("Descripción 7")
                            .pricePerHour(2000)
                            .maxCapacity(400)
                            .topics(List.of(
                                    competitiveGame,
                                    teamBuildingDynamic,
                                    party,
                                    dancingLunch,
                                    picnicLunch,
                                    anniversary
                            )).build(),
                    EstablishmentEntity.builder()
                            .name("Area deportiva N1")
                            .description("Descripción 8")
                            .pricePerHour(2000)
                            .maxCapacity(800)
                            .topics(List.of(
                                    sportsEvent,
                                    sportsChampionship,
                                    party,
                                    lunch,
                                    institutionalEvent
                            )).build(),
                    EstablishmentEntity.builder()
                            .name("Area deportiva N2")
                            .description("Descripción 9")
                            .pricePerHour(2000)
                            .maxCapacity(800)
                            .topics(List.of(sportsChampionship)).build(),
                    EstablishmentEntity.builder()
                            .name("Zona de camping")
                            .description("Descripción 10")
                            .pricePerHour(2000)
                            .maxCapacity(1800)
                            .topics(List.of(
                                    institutionalEvent,
                                    endOfYearEvent,
                                    dancingLunch,
                                    pachamancas,
                                    simultaneousActivities
                            )).build()
            );

            for (EstablishmentEntity establishment : establishments) {
                var establishmentSaved = this.establishmentRepository.save(establishment);
                System.out.println(establishmentSaved);
                this.establishmentImageRepository.save(EstablishmentImageEntity.builder()
                        .imageUrl("https://test_images.com/image.png")
                        .establishment(establishmentSaved)
                        .build());
            }
        };
    }
}

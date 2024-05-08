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

import java.util.ArrayList;
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
                            .description("Con capacidad para 12 personas, nuestra Sala de Directorio ofrece tecnología de proyección multimedia de vanguardia y un moderno ecran para presentaciones impactantes. Además, cuenta con una terraza adyacente para servicios de \"coffee break\", proporcionando un ambiente tranquilo para recargar energías entre reuniones.")
                            .pricePerHour(9000)
                            .maxCapacity(12)
                            .topics(List.of(multimediaProjection))
                            .build(),
                    EstablishmentEntity.builder()
                            .name("Sala el fundador")
                            .description("Con una capacidad máxima para 80 personas, la Sala El Fundador es el espacio ideal para una variedad de eventos, desde conferencias y talleres hasta ceremonias y workshops. Equipada con tecnología de punta, incluyendo proyector multimedia, ecran, sistema de sonido centralizado y micrófono inalámbrico, garantiza una experiencia audiovisual impecable y una comunicación clara para todos los asistentes. Además, la Sala El Fundador ofrece la versatilidad de oscurecimiento con su sistema \"blackout\", permitiendo ajustar la iluminación según las necesidades del evento. Ya sea para sesiones educativas o eventos formales, este espacio proporciona el ambiente adecuado para cada ocasión.")
                            .pricePerHour(26000)
                            .maxCapacity(80)
                            .topics(List.of(multimediaProjection, conference, workshop, school, ceremony))
                            .build(),
                    EstablishmentEntity.builder()
                            .name("Sala de piedra")
                            .description("Con capacidad para hasta 120 personas, la Sala de Piedra se adapta a una variedad de eventos, desde conferencias y cocktails hasta almuerzos y workshops. Equipada con el mobiliario y equipamiento necesarios para cada ocasión, ofrece una vista panorámica a la zona de piscinas y áreas verdes, creando un entorno inspirador y relajante para reuniones y actividades sociales.")
                            .pricePerHour(25500)
                            .maxCapacity(120)
                            .topics(List.of(conference, lunch, productPresentation, workshop))
                            .build(),
                    EstablishmentEntity.builder()
                            .name("Restaurante el mesón")
                            .description("Con una capacidad máxima para 200 personas, el Restaurante El Mesón es el lugar perfecto para una variedad de eventos, desde almuerzos y cenas hasta cocktails, matrimonios y aniversarios. Su versatilidad se refleja en su capacidad de adaptarse al armado y la decoración específica para cada ocasión, asegurando una experiencia única y personalizada para cada evento.")
                            .pricePerHour(280000)
                            .maxCapacity(200)
                            .topics(List.of(lunch, dinner, marriage, anniversary))
                            .build(),
                    EstablishmentEntity.builder()
                            .name("Anfiteatro")
                            .description("El Anfiteatro es un espacio único que combina áreas verdes con un escenario natural de piedra, creando un entorno impresionante para una variedad de eventos. Con una capacidad máxima para 600 personas, este lugar ofrece infraestructura y equipos adaptados a la ocasión, desde fiestas y almuerzos bailables hasta aniversarios y eventos institucionales. Además, cuenta con una infraestructura fija diseñada específicamente para la actuación de orquestas, garantizando una experiencia musical excepcional para los asistentes.")
                            .pricePerHour(25000)
                            .maxCapacity(600)
                            .topics(List.of(party, dancingLunch, anniversary, institutionalEvent))
                            .build(),
                    EstablishmentEntity.builder()
                            .name("Explanada baja de piscina")
                            .description("Situada en un entorno natural impresionante, la Explanada Baja de Piscina ofrece áreas verdes de 1500 m2, rodeadas de árboles y con vista a una laguna natural. Con una capacidad máxima de 800 personas, este espacio es ideal para almuerzos campestres, dinámicas de \"team building\", fiestas, almuerzos bailables, aniversarios y juegos de competencia. La infraestructura y los equipos se adaptan a la ocasión, asegurando una experiencia memorable y exitosa para todos los eventos celebrados aquí.")
                            .pricePerHour(26000)
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
                            .description("Rodeada de áreas verdes y equipada con piscinas de esparcimiento para adultos y niños, la Explanada de Piscina Alta ofrece un ambiente acuático relajante y divertido para eventos de hasta 400 personas. La infraestructura y el equipamiento se adaptan a cada ocasión, desde juegos de competencia acuáticos y dinámicas de \"team building\" hasta fiestas, almuerzos bailables, almuerzos campestres, aniversarios y otros eventos especiales. Este espacio versátil proporciona una experiencia única y refrescante para todas las celebraciones.")
                            .pricePerHour(28000)
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
                            .description("Con una amplia explanada de áreas verdes de 1800 m2, el Área Deportiva N° 1 es el escenario perfecto para una variedad de eventos. Con capacidad para 800 personas, este espacio es ideal para eventos deportivos, campeonatos de fulbito y/o vóley, almuerzos, fiestas bailables y eventos institucionales. La infraestructura y el equipamiento se adaptan meticulosamente a la ocasión, garantizando un ambiente óptimo y seguro para todas las actividades celebradas aquí.")
                            .pricePerHour(26000)
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
                            .description("Con una explanada de áreas verdes de 1600 m2, el Área Deportiva N° 2 es un espacio versátil diseñado para una amplia gama de actividades deportivas y recreativas. Ideal para campeonatos deportivos de fulbito y vóley, juegos de competencia, rapel y más, este lugar ofrece el escenario perfecto para emocionantes desafíos y actividades al aire libre.")
                            .pricePerHour(25000)
                            .maxCapacity(800)
                            .topics(List.of(sportsChampionship)).build(),
                    EstablishmentEntity.builder()
                            .name("Zona de camping")
                            .description("Con una vasta explanada de áreas verdes de 8000 m2, la Zona de Camping ofrece un espacio natural y espacioso para una variedad de eventos. Con capacidad para hasta 1800 personas, este lugar es ideal para eventos institucionales, dinámicas grupales, eventos de fin de año, almuerzos bailables, pachamancas y más. Además, su área adecuada para actividades en simultáneo permite la realización de múltiples actividades al mismo tiempo, brindando opciones para todos los gustos y edades. La infraestructura y el equipamiento se adaptan cuidadosamente a cada ocasión, garantizando una experiencia cómoda y memorable para todos los participantes.")
                            .pricePerHour(25000)
                            .maxCapacity(1800)
                            .topics(List.of(
                                    institutionalEvent,
                                    endOfYearEvent,
                                    dancingLunch,
                                    pachamancas,
                                    simultaneousActivities
                            ))
                            .build()
            );

            var savedEstablishments = establishmentRepository.saveAll(establishments);

            var establishmentImages = List.of(
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042627/RESERVACIONES_SAR/SALON%20DE%20DIRECTORIO/SALON_DIRECTORIO_1_zbeqsr.jpg")
                            .establishment(savedEstablishments.get(0))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042629/RESERVACIONES_SAR/SALON%20DE%20DIRECTORIO/SALON_DIRECTORIO_2_fkulxp.jpg")
                            .establishment(savedEstablishments.get(0))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042630/RESERVACIONES_SAR/SALON%20DE%20DIRECTORIO/SALON_DIRECTORIO_3_ee6iwi.jpg")
                            .establishment(savedEstablishments.get(0))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042626/RESERVACIONES_SAR/SALA%20EL%20FUNDADOR/SALA_FUNDADOR_3_sqjbvb.jpg")
                            .establishment(savedEstablishments.get(1))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042624/RESERVACIONES_SAR/SALA%20EL%20FUNDADOR/SALA_FUNDADOR_2_v2rirr.jpg")
                            .establishment(savedEstablishments.get(1))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042623/RESERVACIONES_SAR/SALA%20EL%20FUNDADOR/SALA_FUNDADOR_1_cge9j4.jpg")
                            .establishment(savedEstablishments.get(1))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042619/RESERVACIONES_SAR/SALA%20DE%20PIEDRA/SALA_PIEDRA_1_q6ardl.jpg")
                            .establishment(savedEstablishments.get(2))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042620/RESERVACIONES_SAR/SALA%20DE%20PIEDRA/SALA_PIEDRA_2_mihhvr.jpg")
                            .establishment(savedEstablishments.get(2))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042622/RESERVACIONES_SAR/SALA%20DE%20PIEDRA/SALA_PIEDRA_3_mfbz71.jpg")
                            .establishment(savedEstablishments.get(2))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042618/RESERVACIONES_SAR/RESTAURANTE%20EL%20MESON/RESTAURANTE_3_mbdihn.jpg")
                            .establishment(savedEstablishments.get(3))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042616/RESERVACIONES_SAR/RESTAURANTE%20EL%20MESON/RESTAURANTE_2_ll5nbj.jpg")
                            .establishment(savedEstablishments.get(3))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042615/RESERVACIONES_SAR/RESTAURANTE%20EL%20MESON/RESTAURANTE_1_cjgtkb.jpg")
                            .establishment(savedEstablishments.get(3))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042636/RESERVACIONES_SAR/ANFITEATRO/ANFITEATRO_2_mjpxnz.jpg")
                            .establishment(savedEstablishments.get(4))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042635/RESERVACIONES_SAR/ANFITEATRO/ANFITEATRO_1_ciapla.jpg")
                            .establishment(savedEstablishments.get(4))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042656/RESERVACIONES_SAR/ANFITEATRO/ANFITEATRO_3_s5mrzt.jpg")
                            .establishment(savedEstablishments.get(4))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042611/RESERVACIONES_SAR/EXPLANADA%20BAJA%20DE%20PISCINA/EXPLANADA_BAJA_3_jp3etm.jpg")
                            .establishment(savedEstablishments.get(5))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042609/RESERVACIONES_SAR/EXPLANADA%20BAJA%20DE%20PISCINA/EXPLANADA_BAJA_2_ombfew.jpg")
                            .establishment(savedEstablishments.get(5))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042608/RESERVACIONES_SAR/EXPLANADA%20BAJA%20DE%20PISCINA/EXPLANADA_BAJA_1_bwz8xp.jpg")
                            .establishment(savedEstablishments.get(5))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042612/RESERVACIONES_SAR/EXPLANADA%20DE%20PISCINA%20ALTA/EXPLANADA_ALTA_1_il2rc5.jpg")
                            .establishment(savedEstablishments.get(6))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042614/RESERVACIONES_SAR/EXPLANADA%20DE%20PISCINA%20ALTA/EXPLANADA_ALTA_3_qssq0t.jpg")
                            .establishment(savedEstablishments.get(6))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042613/RESERVACIONES_SAR/EXPLANADA%20DE%20PISCINA%20ALTA/EXPLANADA_ALTA_2_y279sv.jpg")
                            .establishment(savedEstablishments.get(6))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042603/RESERVACIONES_SAR/AREA%20DEPORTIVA%201/AREA_DEPORTIVA_1_3_wcwuxq.jpg")
                            .establishment(savedEstablishments.get(7))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042602/RESERVACIONES_SAR/AREA%20DEPORTIVA%201/AREA_DEPORTIVA_1_2_tytuom.jpg")
                            .establishment(savedEstablishments.get(7))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042602/RESERVACIONES_SAR/AREA%20DEPORTIVA%201/AREA_DEPORTIVA_1_1_ui9zg4.jpg")
                            .establishment(savedEstablishments.get(7))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042607/RESERVACIONES_SAR/AREA%20DEPORTIVA%202/AREA_DEPORTIVA_2_3_odmib6.jpg")
                            .establishment(savedEstablishments.get(8))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042606/RESERVACIONES_SAR/AREA%20DEPORTIVA%202/AREA_DEPORTIVA_2_2_uvurfk.jpg")
                            .establishment(savedEstablishments.get(8))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042605/RESERVACIONES_SAR/AREA%20DEPORTIVA%202/AREA_DEPORTIVA_2_1_t5lzw1.jpg")
                            .establishment(savedEstablishments.get(8))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042632/RESERVACIONES_SAR/ZONA%20DE%20CAMPING/ZONA_CAMPING_2_xd5tou.jpg")
                            .establishment(savedEstablishments.get(9))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042633/RESERVACIONES_SAR/ZONA%20DE%20CAMPING/ZONA_CAMPING_3_egnb5c.jpg")
                            .establishment(savedEstablishments.get(9))
                            .build(),
                    EstablishmentImageEntity.builder()
                            .imageUrl("https://res.cloudinary.com/dyj76ypg3/image/upload/v1715042631/RESERVACIONES_SAR/ZONA%20DE%20CAMPING/ZONA_CAMPING_1_pzebqt.jpg")
                            .establishment(savedEstablishments.get(9))
                            .build()
            );

            establishmentImageRepository.saveAll(establishmentImages);
        };
    }
}

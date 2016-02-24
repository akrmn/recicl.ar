package ar.recicl.reciclar.data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ar.recicl.reciclar.application.Application;

public class Message {
    private static List<String> mMessages = Arrays.asList(
            "%s ha reciclado %d botellas de plástico en la Avenida Cinco de Julio",
            "%s ha entregado %dkg de periódico en el Centro de Reciclaje en Recoleta",
            "%s ha reciclado %d botellas de vino en la última semana",
            "%s alcanzó %d botellas de plástico recicladas este mes",
            "%s ha reciclado %d latas de aluminio esta semana y lidera la tabla de metas.",
            "%s logró motivar a sus seguidores logrando que ellos reciclen %d toneladas de cartón. ",
            "%s en colaboración con Empresas Alumitech, recicló %d00 latas de bebidas gaseosas.",
            "%s hizo un aporte de %d botellas de vidrio a su meta y se acerca a la cúspide de la tabla.",
            "%s junto a Supermercados Leinad recicló %d toneladas de desechos orgánicos. ",
            "%s depositó en los vertederos de Plaza de Mayo %d botellas de vidrio.",
            "%s completó su record de esta semana al reciclar %d cajas de pizza.",
            "%s alcanzó la cantidad de %d botellas de vidrio recicladas en vertederos de Buenos Aires.",
            "%s no finalizó con éxito su meta de recabar %d latas de aluminio.",
            "%s se unió al reto de Daniel Sarcos y harán campañas publicitarias por el reciclaje en la Argentina."
    );

    public static String anyMessage() {
        int index = Application.sRandom.nextInt(mMessages.size());
        return String.format(mMessages.get(index), "%s", Application.sRandom.nextInt(20) + 2);
    }
}

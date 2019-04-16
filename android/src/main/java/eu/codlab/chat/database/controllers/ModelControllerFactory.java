package eu.codlab.chat.database.controllers;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.config.FlowManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import eu.codlab.chat.database.models.ChatMessageType;
import eu.codlab.chat.utils.FixCursorWindow;

public class ModelControllerFactory {
    @NonNull
    private final static HashMap<Class<? extends AbstractController>, AbstractController> sVoxeetRepositories = new HashMap<>();


    public static void init(@NonNull Context context) {
        ChatMessageType.init();
        FlowManager.init(context);
        FixCursorWindow.fix();

        instantiate(new Class[]{
                ChatMessageController.class,
                ConversationController.class,
                ConversationUserController.class,
                UserController.class
        });
    }

    @NonNull
    public static void instantiate(Class[] array) {
        for (Class klass : array) {
            getOrCreate(klass);
        }
    }

    @NonNull
    public static <VR extends AbstractController> VR getOrCreate(@NonNull Class<VR> klass) {
        AbstractController repo = get(klass);
        if (null == repo) {
            VR instance = instantiate(klass);
            sVoxeetRepositories.put(klass, instance);
            return instance;
        }
        return (VR) repo;
    }

    @NonNull
    public static <VR extends AbstractController> VR get(@NonNull Class<VR> klass) {
        return (VR) sVoxeetRepositories.get(klass);
    }

    private static <VR extends AbstractController> VR instantiateSimple(@NonNull Class<VR> klass, boolean useSdk) {
        try {
            Constructor constructor = null;

            if (useSdk) {
                constructor = klass.getConstructor();
                return (VR) constructor.newInstance();
            }
            constructor = klass.getConstructor();
            return (VR) constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static <VR extends AbstractController> VR instantiate(@NonNull Class<VR> klass) {
        VR instance = instantiateSimple(klass, true);
        if (null != instance) return instance;
        instance = instantiateSimple(klass, false);
        return instance;
    }

}

package me.xtrm.Atlas.utils;

import java.lang.reflect.Field;
import java.net.Proxy;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class SessionUtils {
	
	public static Session createPremiumSession(String username, String password) throws AuthenticationException {
        YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) service.createUserAuthentication(Agent.MINECRAFT);

        auth.setUsername(username);
        auth.setPassword(password);

        auth.logIn();
        return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
    }
	
	public static Session createCrackedSession(String username) {
		return new Session(username, "", "", "mojang");
	}
	
	public static void setSession(Session session) {
		try {
			Field f = Minecraft.getMinecraft().getClass().getDeclaredField("session");
			f.setAccessible(true);
			f.set(Minecraft.getMinecraft(), session);
		} catch(Exception e) {
			try {
				Field f = Minecraft.getMinecraft().getClass().getDeclaredField("field_71449_j");
				f.setAccessible(true);
				f.set(Minecraft.getMinecraft(), session);
			} catch(Exception ee) {
				e.printStackTrace();
				ee.printStackTrace();
			}
		}		
	}
}

import org.shoroa.clientbase.features.mods.api.Module;
import org.shoroa.clientbase.features.mods.api.ModuleCategory;
import org.shoroa.clientbase.setting.Setting;
import org.shoroa.clientbase.uitl.rendeer.RenderUtil;
import org.shoroa.clientbase.uitl.rendeer.RoundedUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class PCGuiHandler {

	public RenderUtil ru = new RenderUtil();
	public RoundedUtils ruu = new RoundedUtils();
	public Minecraft mc = Minecraft.getMinecraft();
	public FontRenderer fr = mc.fontRendererObj;
	
	public float x,y,w,h;
	public ModuleCategory category;
	public Module module;
	public Setting setting;
	
	public void drawModule(Module moduleIn, float xIn, float yIn, int mouseX, int mouseY) {}
	public void drawCategory(ModuleCategory moduleCategoryIn, float xIn, float yIn, int mouseX, int mouseY) {}
	public void drawPanel(float xIn, float yIn, float widthIn, float heightIn, int mouseX, int mouseY) {}
	public void drawSetting(Setting setting, float xIn, float yIn, int mouseX, int mouseY) {}
	public void clickModule(Module moduleIn, float xIn, float yIn, int mouseX, int mouseY) {}
	public void clickCategory(ModuleCategory moduleCategoryIn, float xIn, float yIn, int mouseX, int mouseY) {}
	public void clickSetting(Setting setting,Module parent , float xIn, float yIn, int mouseX, int mouseY) {}
	public void mouseReleased(int mouseX, int mouseY, int state) {}
	public RenderUtil getRu() {
		return ru;
	}
	public void setRu(RenderUtil ru) {
		this.ru = ru;
	}
	public RoundedUtils getRuu() {
		return ruu;
	}
	public void setRuu(RoundedUtils ruu) {
		this.ruu = ruu;
	}
	public Minecraft getMc() {
		return mc;
	}
	public void setMc(Minecraft mc) {
		this.mc = mc;
	}
	public FontRenderer getFr() {
		return fr;
	}
	public void setFr(FontRenderer fr) {
		this.fr = fr;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getW() {
		return w;
	}
	public void setW(float w) {
		this.w = w;
	}
	public float getH() {
		return h;
	}
	public void setH(float h) {
		this.h = h;
	}
	public ModuleCategory getCategory() {
		return category;
	}
	public void setCategory(ModuleCategory category) {
		this.category = category;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
}

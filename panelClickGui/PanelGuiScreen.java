
import java.io.IOException;

import org.shoroa.clientbase.Client;
import org.shoroa.clientbase.features.mods.api.Module;
import org.shoroa.clientbase.features.mods.api.ModuleCategory;
import org.shoroa.clientbase.setting.Setting;

import net.minecraft.client.gui.GuiScreen;

public class PanelGuiScreen extends GuiScreen {

	public PCGui pcg;
	
	public PanelGuiScreen() {
		int x=180,y = 100;
		pcg = new PCGui(Client.INSTANCE.x, Client.INSTANCE.y, x*2, y*2);
	}
	
	@Override
	public void initGui() {
		Client.INSTANCE.config.load();
		super.initGui();
	}
	@Override
	public void onGuiClosed() {
		Client.INSTANCE.config.save();
		super.onGuiClosed();
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		pcg.drawPanel(pcg.getX(), pcg.getY(),pcg.getW(), pcg.getH(), mouseX, mouseY);
		int countCategory = 0;
		for(ModuleCategory cat : ModuleCategory.values()) {
			pcg.drawCategory(cat, pcg.getX()+6, pcg.getY() + 19 + countCategory, mouseX, mouseY);
			countCategory += 30;
		}
		int countModule = 0;
		int countSettingX = 0;
		int countSettingY = 0;
		for(Module m : Client.INSTANCE.moduleManager.getModulesByCategory(pcg.category)) {
			pcg.drawModule(m, pcg.getX()+6+28+2, pcg.getY() + 19 + countModule, mouseX, mouseY);
			countModule+= 30;
			if(m.showSettings)
			for(Setting s : m.settings) {
				pcg.drawSetting(s, pcg.getX()+28+1+80+countSettingX*84, pcg.getY()+19+countSettingY*30, mouseX, mouseY);
				if(countSettingX < 3)
					countSettingX++;
				if(countSettingX == 3) {
					countSettingX=0;
					countSettingY++;
				}
			}
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		int countModule = 0;
		int countSettingX = 0;
		int countSettingY = 0;
		for(Module m : Client.INSTANCE.moduleManager.getModulesByCategory(pcg.category)) {
			pcg.clickModule(m, pcg.getX()+6+28+2, pcg.getY() + 19 + countModule, mouseX, mouseY);
			for(Setting s : m.settings) {
				if(m.showSettings)
				pcg.clickSetting(s, m, pcg.getX()+28+1+80+countSettingX*84, pcg.getY()+19+countSettingY*30, mouseX, mouseY);
				if(countSettingX < 3)
					countSettingX++;
				if(countSettingX == 3) {
					countSettingX=0;
					countSettingY++;
				}
			}
			countModule+= 30;
		}
		int countCategory = 0;
		for(ModuleCategory cat : ModuleCategory.values()) {
			pcg.clickCategory(cat, pcg.getX()+6, pcg.getY() + 19 + countCategory, mouseX, mouseY);
			countCategory += 30;
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		pcg.mouseReleased(mouseX, mouseY, state);
		super.mouseReleased(mouseX, mouseY, state);
	}
}

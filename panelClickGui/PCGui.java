
import java.awt.Color;

import org.lwjgl.input.Mouse;
import org.shoroa.clientbase.Client;
import org.shoroa.clientbase.features.mods.api.Module;
import org.shoroa.clientbase.features.mods.api.ModuleCategory;
import org.shoroa.clientbase.setting.Setting;
import org.shoroa.clientbase.setting.impl.BooleanSetting;
import org.shoroa.clientbase.setting.impl.ColorSetting;
import org.shoroa.clientbase.setting.impl.ModeSetting;
import org.shoroa.clientbase.setting.impl.NumberSetting;
import org.shoroa.clientbase.uitl.color.Colors;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

public class PCGui extends PCGuiHandler{

    private boolean drag;
	int catDist = 0;

    public double val;
    
    private float dragX;
    private float dragY;
	private boolean isSliding;
    
	public PCGui(float x, float y, float w, float h) {
		setX(x);
		setY(y);
		setW(w);
		setH(h);
	}
	
	
	public void drawCategory(ModuleCategory moduleCategoryIn, float xIn, float yIn, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		boolean hovered = (mouseX > xIn && mouseX < xIn + 28 && mouseY > yIn && mouseY < yIn + 28);
		
		GlStateManager.enableAlpha();
		ruu.drawRoundedRect(xIn, yIn,xIn + 28,yIn + 28, 0, Colors.i.bgColor_1.getRGB());
		new Gui().drawCenteredString(fr, ""+moduleCategoryIn.getName().charAt(0), xIn+28/2, yIn+28/3, -1);
		
		catDist=8;
		
		if(hovered) {
			ru.drawGradient(xIn, yIn, xIn+28, yIn+catDist, Colors.i.bgColor_3.getRGB(), Colors.i.bgColor_1.getRGB());
			ru.drawGradient(xIn, yIn+28-catDist, xIn+28, yIn+28,  Colors.i.bgColor_1.getRGB(), Colors.i.bgColor_3.getRGB());
			ru.drawGradientSideways(xIn,yIn, xIn + catDist, yIn+28, Colors.i.bgColor_3.getRGB(), new Color(Colors.i.bgColor_1.getRed(),Colors.i.bgColor_1.getGreen(),Colors.i.bgColor_1.getBlue(),0).getRGB());
			ru.drawGradientSideways(xIn+28-catDist,yIn, xIn + 28, yIn+28, new Color(Colors.i.bgColor_1.getRed(),Colors.i.bgColor_1.getGreen(),Colors.i.bgColor_1.getBlue(),0).getRGB(), Colors.i.bgColor_3.getRGB());
		}
		ruu.drawRoundedOutline(xIn, yIn, xIn+28, yIn+28, 0, 1, Colors.i.bgColor_4.getRGB());
		super.drawCategory(moduleCategoryIn, xIn, yIn, mouseX, mouseY);
	}
	
	public void drawModule(Module moduleIn, float xIn, float yIn, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		ruu.drawRoundedRect(xIn, yIn,xIn + 60,yIn + 28, 0, Colors.i.bgColor_1.getRGB());
		ruu.drawRoundedRect(xIn+51, yIn, xIn+60, yIn+28, 0, Colors.i.bgColor_4.getRGB());
		if(!moduleIn.equals(Client.INSTANCE.moduleManager.clickgui))
			ruu.drawRoundedRect(xIn+51, yIn, xIn+60, yIn+28, 0, !moduleIn.isEnabled() ? Colors.i.bgColor_2.getRGB() : Colors.i.bgColorAura_2.getRGB());
		else
			ruu.drawRoundedRect(xIn+51, yIn, xIn+60, yIn+28, 0,Colors.i.bgColorAura_2.getRGB());
		ruu.drawRoundedOutline(xIn, yIn, xIn+60, yIn+28, 0, 1, Colors.i.bgColor_4.getRGB());
		fr.drawStringWithShadow(moduleIn.name, xIn+2, yIn+28/3, -1);
		super.drawModule(moduleIn, xIn, yIn, mouseX, mouseY);
	}
	
	public void drawPanel(float xIn, float yIn, float widthIn, float heightIn, int mouseX, int mouseY) {
		
		boolean hoveredDragBox = (mouseX > xIn && mouseX < xIn + widthIn && mouseY > yIn && mouseY < yIn + 16);
		
		if(!this.drag && Mouse.isButtonDown(0))drag = false;
        if (this.drag) {
        	setX(mouseX + this.dragX);
        	setY(mouseY + this.dragY);
            if(!Mouse.isButtonDown(0)) {
            	this.drag = false;
            }
        }
		if(hoveredDragBox && Mouse.isButtonDown(0)) {
    		if(x<0)setX(0);
    		if(y<0)setY(0);
			if (!this.drag) {
				this.dragX = x - mouseX;
            	this.dragY = y - mouseY;
            	this.drag = true;
            }
		}
		
		ruu.drawRoundedRect(xIn, yIn,xIn + widthIn,yIn + heightIn, 16, Colors.i.bgColor_1.getRGB());
		ruu.drawSelectRoundedRect(xIn, yIn,xIn + 106,yIn + heightIn, 16,16,0,0, Colors.i.bgColor_2.getRGB());
		ruu.drawSelectRoundedRect(xIn, yIn,xIn + widthIn,yIn + 16, 16,0,0,16, Colors.i.bgColor_3.getRGB());
		ruu.drawRoundedOutline(xIn, yIn, xIn + widthIn, yIn + heightIn, 16, 3, Colors.i.bgColor_4.getRGB());
		GlStateManager.color(1, 1, 1, 1);
		super.drawPanel(xIn, yIn, widthIn, heightIn, mouseX, mouseY);
	}
	
	public void drawSetting(Setting setting, float xIn, float yIn, int mouseX, int mouseY) {
		boolean hovered = (mouseX > xIn && mouseX < xIn + 80 && mouseY > yIn && mouseY < yIn + 28);
		if(setting instanceof BooleanSetting) {
			ruu.drawRoundedRect(xIn, yIn, xIn+80, yIn+28, 1, Colors.i.bgColor_3.getRGB());
			ruu.drawRoundedRect(xIn+72, yIn, xIn+80, yIn+28, 1, ((BooleanSetting) setting).isEnabled() ? Colors.i.bgColor_2.getRGB() : Colors.i.bgColorAura_2.getRGB());
			ruu.drawRoundedOutline(xIn, yIn, xIn+80, yIn+28, 0, 1, Colors.i.bgColor_4.getRGB());
			if(setting.name.length() < 11) {
				fr.drawStringWithShadow(setting.name, xIn+4, yIn+28/3, -1);
			} else {
				String[] strings = setting.name.split(" ");
				fr.drawStringWithShadow(strings[0], xIn+4, yIn+28/6, -1);
				fr.drawStringWithShadow(strings[1], xIn+4, yIn+28/1.8f, -1);
			}
		}
		if(setting instanceof NumberSetting) {
            NumberSetting ns = (NumberSetting) setting;
            if(isSliding){
                float value = (float)(mouseX - (xIn)) / (float)((80)/ ns.getMaximum());
                value = clamp_float(value, (float) ns.getMinimum(), (float) ns.getMaximum());
                float toNormal = this.denormalizeValue(value, ns);
                ns.setValue(value);
                value = this.normalizeValue(toNormal,ns);
            }
			ruu.drawRoundedRect(xIn, yIn, xIn+80, yIn+28, 1, Colors.i.bgColor_3.getRGB());
            if(hovered) {
            	ruu.drawRoundedRect(xIn, yIn, (float) ((xIn) + (ns.getValue()/ ns.getMaximum()*80)), yIn+28, 1, Colors.i.bgColorAura_2.getRGB());
            }
            if(!hovered) {
            	ruu.drawRoundedRect(xIn, yIn, (float) ((xIn) + (ns.getValue()/ ns.getMaximum()*80)), yIn+28, 1, Colors.i.bgColor_2.getRGB());
            }
            if(!hovered && isSliding) {
            	ruu.drawRoundedRect(xIn, yIn, (float) ((xIn) + (ns.getValue()/ ns.getMaximum()*80)), yIn+28, 1, Colors.i.bgColorAura_2.getRGB());
            }
			ruu.drawRoundedOutline(xIn, yIn, xIn+80, yIn+28, 0, 1, Colors.i.bgColor_4.getRGB());
			if(setting.name.length() < 11) {
				fr.drawStringWithShadow(setting.name + " " + ns.getValue(), xIn+4, yIn+28/3, -1);
			} else {
				String[] strings = setting.name.split(" ");
				fr.drawStringWithShadow(strings[0] + " " + ns.getValue(), xIn+4, yIn+28/6, -1);
				fr.drawStringWithShadow(strings[1] + " " + ns.getValue(), xIn+4, yIn+28/1.8f, -1);
			}
		}
		if(setting instanceof ModeSetting) {
			ruu.drawRoundedOutline(xIn, yIn, xIn+80, yIn+28, 0, 1, -1);
		}
		if(setting instanceof ColorSetting) {
			ruu.drawRoundedOutline(xIn, yIn, xIn+80, yIn+28, 0, 1, -1);
		}
		super.drawSetting(setting, xIn, yIn, mouseX, mouseY);
	}
	
	public void clickSetting(Setting setting, Module parent, float xIn, float yIn, int mouseX, int mouseY) {
		boolean hovered = (mouseX > xIn && mouseX < xIn + 80 && mouseY > yIn && mouseY < yIn + 28);
		if(hovered) {
			if(setting instanceof BooleanSetting) {
				((BooleanSetting) setting).toggle();
			}
			if(setting instanceof NumberSetting) {
				this.isSliding = true;
			}
			if(setting instanceof ModeSetting) {
			}
			if(setting instanceof ColorSetting) {
			}
		}
		super.clickSetting(setting, parent, xIn, yIn, mouseX, mouseY);
	}

	
    public void mouseReleased(int mouseX, int mouseY, int state)
    {
        isSliding = false;
    }
    
	
	public void clickCategory(ModuleCategory moduleCategoryIn, float xIn, float yIn,  int mouseX, int mouseY) {
		boolean hovered = (mouseX > xIn && mouseX < xIn + 28 && mouseY > yIn && mouseY < yIn + 28);
		if(hovered && Mouse.isButtonDown(0)) {
			category=moduleCategoryIn;
		}
		super.clickCategory(moduleCategoryIn, xIn, yIn,  mouseX, mouseY);
	}
	
	public void clickModule(Module moduleIn, float xIn, float yIn, int mouseX, int mouseY) {
		boolean hovered = (mouseX > xIn && mouseX < xIn + 60 && mouseY > yIn && mouseY < yIn + 28);
		if(hovered && Mouse.isButtonDown(0))
			if(!moduleIn.equals(Client.INSTANCE.moduleManager.clickgui))
				moduleIn.toggle();
		if(hovered)
			if(Mouse.isButtonDown(1)) {
				moduleIn.showSettings = !moduleIn.showSettings;
			}
		super.clickModule(moduleIn,xIn, yIn, mouseX, mouseY);
	}
	
	
    public float normalizeValue(float val , NumberSetting set)
    {
        return clamp_float((float) ((this.snapToStepClamp(val ,set) - set.getMinimum()) / (set.getMaximum() - set.getMinimum())), 0.0F, 1.0F);
    }

    public float denormalizeValue(float p_148262_1_ , NumberSetting set)
    {
        return this.snapToStepClamp((float) (set.getMinimum() + (set.getMaximum() - set.getMinimum()) * clamp_float(p_148262_1_, 0.0F, 1.0F)),set);
    }

    public float snapToStepClamp(float value , NumberSetting set)
    {
        value = this.snapToStep(value,set);
        return clamp_float(value, (float) set.getMinimum(),(float)  set.getMaximum());
    }

    public float snapToStep(float value  , NumberSetting set)
    {
        if (set.getIncrement() > 0.0F)
        {
        	value = (float) (set.getIncrement() * (float)Math.round(value / set.getIncrement()));
        }

        return value;
    }
    public float clamp_float(float num, float min, float max)
    {
        return num < min ? min : (num > max ? max : num);
    }
}

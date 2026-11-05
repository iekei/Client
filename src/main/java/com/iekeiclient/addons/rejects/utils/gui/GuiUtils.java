package com.iekeiclient.addons.rejects.utils.gui;

import com.iekeiclient.addons.rejects.mixin.iekei.GuiRendererAccessor;
import com.iekeiclient.engine.gui.renderer.GuiRenderer;
import com.iekeiclient.engine.gui.widgets.WWidget;
import com.iekeiclient.engine.renderer.Renderer2D;
import com.iekeiclient.engine.utils.render.color.Color;

public class GuiUtils {
    public static void quadRounded(GuiRenderer renderer, double x, double y, double width, double height, Color color, double round, boolean roundTop) {
        Renderer2D mb = ((GuiRendererAccessor)renderer).getRenderer2D();
        RoundedRenderer2D.quadRounded(mb, x, y, width, height, color, round, roundTop);
    }
    public static void quadRounded(GuiRenderer renderer, double x, double y, double width, double height, Color color, double round) {
        quadRounded(renderer, x, y, width, height, color, round, true);
    }
    public static void quadRounded(GuiRenderer renderer, WWidget widget, Color color, double round) {
        quadRounded(renderer, widget.x, widget.y, widget.width, widget.height, color, round);
    }
    public static void quadOutlineRounded(GuiRenderer renderer, double x, double y, double width, double height, Color color, double round, double s) {
        Renderer2D mb = ((GuiRendererAccessor)renderer).getRenderer2D();
        RoundedRenderer2D.quadRoundedOutline(mb, x, y, width, height, color, round, s);
    }
    public static void quadOutlineRounded(GuiRenderer renderer, WWidget widget, Color color, double round, double s) {
        quadOutlineRounded(renderer, widget.x, widget.y, widget.width, widget.height, color, round, s);
    }
    public static void quadRoundedSide(GuiRenderer renderer, double x, double y, double width, double height, Color color, double r, boolean right) {
        Renderer2D mb = ((GuiRendererAccessor)renderer).getRenderer2D();
        RoundedRenderer2D.quadRoundedSide(mb, x, y, width, height, color, r, right);
    }
    public static void quadRoundedSide(GuiRenderer renderer, WWidget widget, Color color, double round, boolean right) {
        quadRoundedSide(renderer, widget.x, widget.y, widget.width, widget.height, color, round, right);
    }
    public static void circlePart(GuiRenderer renderer, double x, double y, double r, double startAngle, double angle, Color color) {
        Renderer2D mb = ((GuiRendererAccessor)renderer).getRenderer2D();
        RoundedRenderer2D.circlePart(mb, x, y, r, startAngle, angle, color);
    }
    public static void circlePartOutline(GuiRenderer renderer, double x, double y, double r, double startAngle, double angle, Color color, double outlineWidth) {
        Renderer2D mb = ((GuiRendererAccessor)renderer).getRenderer2D();
        RoundedRenderer2D.circlePartOutline(mb, x, y, r, startAngle, angle, color, outlineWidth);
    }
}

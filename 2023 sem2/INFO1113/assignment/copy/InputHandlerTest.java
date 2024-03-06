package WizardTD;

import WizardTD.*;
import WizardTD.Monsters.*;
import WizardTD.Tiles.*;

import processing.event.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;


import static org.junit.jupiter.api.Assertions.*;



public class InputHandlerTest {
    private App test_p;

    private InputHandler inputHandler;

    @BeforeEach
    public void setUp() {
        test_p = new App();
        test_p.loop();
        PApplet.runSketch(new String[] {"App"}, test_p);
        test_p.setup();
        test_p.delay(1000);

        Manager test_manager = new Manager(test_p, "test_config1.json");
        test_manager.CreateGameBoard();
        test_manager.CreateWizardHouse();


        inputHandler = new InputHandler(test_p, test_manager);

    }

    @Test
    public void test_F() {
        assertFalse(inputHandler.isFastForward, "Initially, isFastForward should be false.");

        // 模拟按下 'F' 键
        inputHandler.keyPressed('f');

        assertTrue(inputHandler.isFastForward, "After pressing 'F', isFastForward should be true.");

        // 再次模拟按下 'F' 键，以测试恢复正常速度的功能
        inputHandler.keyPressed('f');

        assertFalse(inputHandler.isFastForward, "After pressing 'F' again, isFastForward should be false.");
    }


    @Test
    public void test_P_TogglePause() {
        assertFalse(inputHandler.isPaused, "Initially, isPaused should be false.");

        // 按下 'P' 键
        inputHandler.keyPressed('p');
        assertTrue(inputHandler.isPaused, "After pressing 'P', isPaused should be true.");

        // 再次按下 'P' 键
        inputHandler.keyPressed('P');
        assertFalse(inputHandler.isPaused, "After pressing 'P' again, isPaused should be false.");
    }

    @Test
    public void test_T_ToggleButtonSelected() {
        assertFalse(inputHandler.buttonSelected[2], "Initially, the third button (for 'T') should not be selected.");

        // 按下 'T' 键
        inputHandler.keyPressed('t');
        assertTrue(inputHandler.buttonSelected[2], "After pressing 'T', the third button should be selected.");

        // 再次按下 'T' 键
        inputHandler.keyPressed('t');
        assertFalse(inputHandler.buttonSelected[2], "After pressing 'T' again, the third button should not be selected.");
    }

    @Test
    public void test_1_ButtonToggle() {

        assertFalse(inputHandler.buttonSelected[3], "Initially, the fourth button (for '1') should not be selected.");

        inputHandler.keyPressed('1');
        assertTrue(inputHandler.buttonSelected[3], "After pressing '1', the fourth button should be selected.");

        inputHandler.keyPressed('1');
        assertFalse(inputHandler.buttonSelected[3], "After pressing '1', the fourth button should be selected.");
    }

    @Test
    public void test_2_ButtonToggle() {

        assertFalse(inputHandler.buttonSelected[4], "Initially, the fourth button (for '1') should not be selected.");

        // 按下 '2' 键
        inputHandler.keyPressed('2');
        assertTrue(inputHandler.buttonSelected[4], "After pressing '1', the fourth button should be selected.");

        // 按下 '2' 键
        inputHandler.keyPressed('2');
        assertFalse(inputHandler.buttonSelected[4], "After pressing '1', the fourth button should be selected.");
    }

    @Test
    public void test_3_ButtonToggle() {
        // 这是一个示例，你可以根据'1'键的具体功能进行修改和补充
        assertFalse(inputHandler.buttonSelected[5], "Initially, the fourth button (for '1') should not be selected.");

        // 按下 '3' 键
        inputHandler.keyPressed('3');
        assertTrue(inputHandler.buttonSelected[5], "After pressing '1', the fourth button should be selected.");

        // 按下 '3' 键
        inputHandler.keyPressed('3');
        assertFalse(inputHandler.buttonSelected[5], "After pressing '1', the fourth button should be selected.");
    }

    @Test
    public void test_M_ButtonPressAndRelease() {
        assertFalse(inputHandler.buttonSelected[6], "Initially, the seventh button (for 'M') should not be selected.");

        // 按下 'M' 键
        inputHandler.keyReleased('c');
        assertFalse(inputHandler.buttonSelected[6], "After pressing 'M', the seventh button should be selected.");

        // 按下 'M' 键
        inputHandler.keyPressed('m');
        assertTrue(inputHandler.buttonSelected[6], "After pressing 'M', the seventh button should be selected.");

        // 按下 'M' 键
        inputHandler.keyPressed('c');
        assertTrue(inputHandler.buttonSelected[6], "After pressing 'M', the seventh button should be selected.");

        // 释放 'M' 键
        inputHandler.keyReleased('M');
        assertFalse(inputHandler.buttonSelected[6], "After releasing 'M', the seventh button should not be selected.");
    }


    @Test
    public void test_other_key() {
        assertFalse(inputHandler.isFastForward, "Initially, isFastForward should be false.");
        // 按下 'M' 键
        inputHandler.keyPressed('x');
        assertFalse(inputHandler.isFastForward, "Initially, isFastForward should be false.");
    }


    @Test
    public void test_checkButtonHover_NoButton() {
        // 假设鼠标未悬停在任何按钮上
        test_p.mouseX = 500;  // 这应该是一个没有按钮的地方的坐标
        test_p.mouseY = 500;

        inputHandler.checkButtonHover();

        assertEquals(-1, inputHandler.getHoveredButtonIndex(), "The hovered button index should be -1 when mouse is not over any button.");
    }

    @Test
    public void test_checkButtonHover_DifferentButtons() {
        int sidebarStartX = 640;
        int buttonWidth = 50;
        int buttonHeight = 50;
        int gap = 10;

        for (int i = 0; i < 7; i++) {
            // Setting the mouse position to the center of the button
            int mouseX = sidebarStartX + 5 + buttonWidth / 2;
            int mouseY = 50 + gap + i * (buttonHeight + gap) + buttonHeight / 2;

            // Set the mouse position
            test_p.mouseX = mouseX;
            test_p.mouseY = mouseY;

            // Call the method
            inputHandler.checkButtonHover();

            // Assert if the hovered button index matches the current button being iterated
            assertEquals(i, inputHandler.getHoveredButtonIndex(), "The hovered button index should be " + i + " when mouse is over the " + (i + 1) + "th button.");
        }
    }

    @Test
    public void test_isButtonSelected(){
        assertFalse(inputHandler.isButtonSelected(10));
        assertFalse(inputHandler.isButtonSelected(-1));

    }

    @Test
    public void test_mousePressed_ButtonActions() {
        int sidebarStartX = 640;
        int buttonWidth = 50;
        int buttonHeight = 50;
        int gap = 10;

        for (int i = 0; i < 7; i++) {
            // 设置鼠标位置到按钮的中心
            int mouseX = sidebarStartX + 5 + buttonWidth / 2;
            int mouseY = 50 + gap + i * (buttonHeight + gap) + buttonHeight / 2;

            // 设置鼠标位置
            test_p.mouseX = mouseX;
            test_p.mouseY = mouseY;

            // Call the method to update the hoveredButtonIndex
            inputHandler.checkButtonHover();

            // Simulate a mouse pressed action
            MouseEvent e = new MouseEvent(null, System.currentTimeMillis(), MouseEvent.PRESS, 0, mouseX, mouseY, PApplet.LEFT, 1);
            inputHandler.mousePressed(e);

            // Assertions based on the button clicked
            switch (i) {
                case 0:
                    assertTrue(inputHandler.isFastForward);
                    assertTrue(inputHandler.buttonSelected[0]); // 确保按钮的状态已更新
                    break;
                case 1:
                    assertTrue(inputHandler.isPaused);
                    assertTrue(inputHandler.buttonSelected[1]);
                    break;
                // ... similarly for other buttons

                case 6: // for the last button as an example
                    assertTrue(inputHandler.buttonSelected[6]);
                    // 添加关于manager.castManaPoolSpell()的断言，如果有相关的检测方法
                    break;
            }

            // 模拟再次点击相同的按钮以取消选择
            inputHandler.mousePressed(e);
            if(i != 6) {
                assertFalse(inputHandler.buttonSelected[i], "Button at index " + i + " should be deselected after a second click.");
            }else{
                assertTrue(inputHandler.buttonSelected[i], "Button at index " + i + " should be deselected after a second click.");
            }
        }
    }


    @Test
    public void test_mousePressed_RightClick_NoAction() {


            // 设置鼠标位置
            test_p.mouseX = 675;
            test_p.mouseY = 50;

            // Call the method to update the hoveredButtonIndex
            inputHandler.checkButtonHover();

            // 模拟鼠标右键点击
            MouseEvent e = new MouseEvent(null, System.currentTimeMillis(), MouseEvent.PRESS, 0, 675, 50, PApplet.RIGHT, 1);
            inputHandler.mousePressed(e);

            assertFalse(inputHandler.buttonSelected[1]);
        }

    @Test
    public void test_mouseReleased_Button6Reset() {
        // 将按钮6设置为选中状态
        inputHandler.buttonSelected[6] = true;

        // 设置鼠标位置到按钮6的中心
        int sidebarStartX = 640;
        int buttonWidth = 50;
        int buttonHeight = 50;
        int gap = 10;
        int i = 6; // 我们只关心索引为6的按钮

        int mouseX = sidebarStartX + 5 + buttonWidth / 2;
        int mouseY = 50 + gap + i * (buttonHeight + gap) + buttonHeight / 2;
        test_p.mouseX = mouseX;
        test_p.mouseY = mouseY;

        // 更新hoveredButtonIndex
        inputHandler.checkButtonHover();

        // 确保按钮6是当前悬停的按钮
        assertEquals(6, inputHandler.getHoveredButtonIndex(), "Button index 6 should be the currently hovered button.");

        // 模拟鼠标释放事件
        MouseEvent e = new MouseEvent(null, System.currentTimeMillis(), MouseEvent.RELEASE, 0, mouseX, mouseY, PApplet.LEFT, 1);
        inputHandler.mouseReleased(e);

        // 确保按钮6的状态已恢复为非选中状态
        assertFalse(inputHandler.buttonSelected[6], "Button at index 6 should be deselected after mouse release.");
    }


}







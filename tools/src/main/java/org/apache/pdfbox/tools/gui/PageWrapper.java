/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.pdfbox.tools.gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;


/**
 * A class to handle some prettyness around a single PDF page.
 * @author Ben Litchfield
 */
public class PageWrapper implements MouseMotionListener, MouseListener
{
    private final JPanel pageWrapper = new JPanel();
    private PDFPagePanel pagePanel = null;
    private final JLabel statusLabel;

    private static final int SPACE_AROUND_DOCUMENT = 20;

    /**
     * Constructor.
     * @param statusLabel The status label to use for status messages.
     * @throws IOException If there is an error creating the page drawing objects.
     */
    public PageWrapper(JLabel statusLabel) throws IOException
    {
        this.statusLabel = statusLabel;
        pagePanel = new PDFPagePanel();
        pageWrapper.setLayout(null);
        pageWrapper.add(pagePanel);
        pagePanel.setLocation(SPACE_AROUND_DOCUMENT, SPACE_AROUND_DOCUMENT);
        pageWrapper.setBorder(LineBorder.createBlackLineBorder());
        pagePanel.addMouseMotionListener(this);
        pagePanel.addMouseListener(this);
    }

    /**
     * 
     * This will display the PDF page in this component.
     * @param renderer The renderer to render the page.
     * @param page The PDF page to display.
     * @param pageNum The number of the page.
     * @throws IOException if something goes wrong.
     */
    public void displayPage(PDFRenderer renderer, PDPage page, int pageNum) throws IOException
    {
        pagePanel.setPage(renderer, page, pageNum);
        pagePanel.setPreferredSize(pagePanel.getSize());
        Dimension d = pagePanel.getSize();
        d.width+=(SPACE_AROUND_DOCUMENT*2);
        d.height+=(SPACE_AROUND_DOCUMENT*2);

        pageWrapper.setPreferredSize(d);
        pageWrapper.validate();
    }

    /**
     * This will get the JPanel that can be displayed.
     * @return The panel with the displayed PDF page.
     */
    public JPanel getPanel()
    {
        return pageWrapper;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        //do nothing when mouse moves.
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        statusLabel.setText(e.getX() + "," + e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        statusLabel.setText("");
    }

}

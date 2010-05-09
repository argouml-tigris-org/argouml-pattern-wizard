/* $Id$
 *****************************************************************************
 * Copyright (c) 2009 Contributors - see below
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    linus
 *****************************************************************************
 *
 * Some portions of this file was previously release using the BSD License:
 */

// Copyright (c) 2006-2007, Rene Lindhorst
// All rights reserved.
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
// * Redistributions of source code must retain the above copyright
//   notice, this list of conditions and the following disclaimer.
// * Redistributions in binary form must reproduce the above copyright
//   notice, this list of conditions and the following disclaimer in
//   the documentation and/or other materials provided with the
//   distribution.
// Neither the name of the Pattern-Wizard project nor the names of its
// contributors may be used to endorse or promote products derived from
// this software without specific prior written permission.
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
// FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
// COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
// INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
// BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
// LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
// CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
// ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

/******************************************************************************\
 *                                 PACKAGE NAME                               *
\******************************************************************************/
package org.argouml.patternwizard;

/******************************************************************************\
 *                                IMPORT SECTION                              *
\******************************************************************************/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import org.apache.log4j.Logger;
import org.argouml.moduleloader.ModuleInterface;
import org.argouml.ui.ProjectBrowser;
import org.argouml.ui.cmd.GenericArgoMenuBar;

/******************************************************************************\
 *                               CLASS DEFINITION                             *
\******************************************************************************/
/**
 * The Pattern-Wizard Module that registers itself to the Tools menu.
 * 
 * @author Rene Lindhorst
 */
public final class ActionOpenPatternWizard implements ModuleInterface, ActionListener
{
    
   /*------------------------------------------------------------------------*\
    |                    P R I V A T E   A T T R I B U T E S                 |
   \*------------------------------------------------------------------------*/  
   
    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(ActionOpenPatternWizard.class);

    /**
     * The menu item for the pattern wizard.
     */
    private JMenuItem patternWizardMenuItem;
    
   /*------------------------------------------------------------------------*\
    |                  P R O T E C T E D   A T T R I B U T E S               |
   \*------------------------------------------------------------------------*/
   
   /*------------------------------------------------------------------------*\
    |                    P A C K A G E   A T T R I B U T E S                 |
   \*------------------------------------------------------------------------*/
   
   /*------------------------------------------------------------------------*\
    |                     P U B L I C   A T T R I B U T E S                  |
   \*------------------------------------------------------------------------*/
    
   /*------------------------------------------------------------------------*\
    |                          C O N S T R U C T O R S                       |
   \*------------------------------------------------------------------------*/
   
    /**
     * Creates a new instance of ActionOpenPatternWizard.
     */
    public ActionOpenPatternWizard()
    {        
        patternWizardMenuItem = new JMenuItem("Pattern-Wizard...");
        patternWizardMenuItem.addActionListener(this);

        LOG.info("Module Pattern-Wizard loaded!");
        
        enable();
    }
    
   /*------------------------------------------------------------------------*\
    |                     P R O T E C T E D   M E T H O D S                  |
   \*------------------------------------------------------------------------*/
   
   /*------------------------------------------------------------------------*\
    |                       P A C K A G E   M E T H O D S                    |
   \*------------------------------------------------------------------------*/
   
   /*------------------------------------------------------------------------*\
    |                       P R I V A T E   M E T H O D S                    |
   \*------------------------------------------------------------------------*/
   
   /*------------------------------------------------------------------------*\
    |                       P U B L I C    M E T H O D S                     |
   \*------------------------------------------------------------------------*/

    ////////////////////////////////////////////////////////////////////////////
    // ModuleInterface implementation                                         //
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Add the Pattern-Wizard MenuItem from the Tools menu. 
     * 
     * @see ModuleInterface#enable()
     */
    public boolean enable() {
        // Register into the Tools menu.
        GenericArgoMenuBar menubar = (GenericArgoMenuBar) ProjectBrowser.getInstance().getJMenuBar();
        menubar.getTools().add(patternWizardMenuItem);
        
        LOG.info("Module Pattern-Wizard enabled!");
        
        return true;
    }

    /**
     * Remove the Pattern-Wizard MenuItem from the Tools menu. 
     * 
     * @see ModuleInterface#disable()
     */
    public boolean disable() {
        GenericArgoMenuBar menubar = (GenericArgoMenuBar) ProjectBrowser.getInstance().getJMenuBar();
        menubar.getTools().remove(patternWizardMenuItem);
        
        LOG.info("Module Pattern-Wizard disabled!");
        
        return true;
    }

    /**
     * @see ModuleInterface#getName()
     */
    public String getName() {
        return "Pattern-Wizard";
    }

    /**
     * @see ModuleInterface#getInfo(int)
     */
    public String getInfo(int type) {
        switch (type) {
            case DESCRIPTION:
                return "The Pattern-Wizard Module adds the possibility to reengineer software systems and integrate design patterns.";
            case AUTHOR:
                return "Rene Lindhorst";
            case VERSION:
                return "@VERSION@";
            case DOWNLOADSITE:
                return "http://argouml-pattern-wizard.tigris.org";
            default:
                return null;
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // ActionListener implementation                                          //
    ////////////////////////////////////////////////////////////////////////////

    /**
     * This method detects when the user clicked the Pattern-Wizard menu item.
     * 
     * @param event The ActionEvent.
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent event) {
        // let the tester know that the pattern-wizard got executed
        LOG.debug("User clicked on '" + event.getActionCommand() + "'");

        // open the Pattern-Wizard
        PatternWizard.getInstance().openPatternWizard();
    }
}

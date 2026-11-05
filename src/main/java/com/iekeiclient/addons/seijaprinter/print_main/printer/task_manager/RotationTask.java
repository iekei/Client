/*
 * Copyright 2025 Nippaku_Zanmu
 * SPDX-License-Identifier: gplv3
 */

package com.iekeiclient.addons.seijaprinter.print_main.printer.task_manager;

import com.iekeiclient.addons.seijaprinter.print_main.printer.util.records.RotationData;

public class RotationTask extends Task{
    public RotationData rdata;

    public RotationTask(Runnable task,RotationData rdata) {
        super(task);
        this.rdata = rdata;
    }
}

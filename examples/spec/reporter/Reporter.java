/*
 *
 * Copyright (c) 2000-2005 Standard Performance Evaluation Corporation (SPEC) All
 * rights reserved. Copyright (c) 2000-2005 Hewlett-Packard All rights reserved.
 * Copyright (c) 1997-2005 Sun Microsystems, Inc. All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 *
 */
package spec.reporter;

import java.io.File;

public class Reporter {
    // This goes right after each class/interface statement
    static final String COPYRIGHT = "SPECjbb2005,"
            + "Copyright (c) 2000-2005 Standard Performance Evaluation Corporation (SPEC),"
            + "All rights reserved,"
            + "Copyright (c) 2000-2005 Hewlett-Packard,"
            + "All rights reserved,"
            + "Copyright (c) 1997-2005 Sun Microsystems, Inc."
            + "All rights reserved,"
            + "Licensed Materials - Property of SPEC";

    // /////////////////////////////////////
    // class variable field declarations
    // /////////////////////////////////////
    /*
     * Command line options
     */
    private static boolean opta = false;

    private static boolean opte = true;

    private static boolean opth = false;

    private static boolean opts = false;

    private static String optl;

    private static String optn;

    private static String opto;

    private static String optr;

    private static String optc;

    private static boolean optv = false;

    public static void main(String[] args) {
        String oldprop = System.setProperty("java.awt.headless", "true");
        if (!getOpt(args))
            return;
        if (opta) {
            TextiReport r = new TextiReport(optn, optr, opts);
            if (opto == null)
                r.print(System.out);
            else
                r.print(opto);
        } else {
            String output_directory = ".";
            if (opto == null)
                output_directory = ".";
            else
                output_directory = new File(opto).getParent();
            if (output_directory == null)
                output_directory = ".";
            Report r = new Report(opte, opts, optn, optr, optv, optc, optl,
                    opth, output_directory);
            if (opto == null)
                r.print(System.out);
            else
                r.print(opto);
            System.out.println(r.messages());
        }
    }

    private static boolean getOpt(String[] args) {
        try {
            for (int i = 0; i < args.length; i++)
                if (args[i].equals("-a"))
                    opta = true;
                else if (args[i].equals("-e"))
                    opte = false;
                else if (args[i].equals("-h")) // amt
                    opth = true;
                else if (args[i].equals("-s"))
                    opts = true;
                else if (args[i].equals("-l")) // amt
                    optl = args[++i];
                else if (args[i].equals("-n"))
                    optn = args[++i];
                else if (args[i].equals("-o"))
                    opto = args[++i];
                else if (args[i].equals("-r"))
                    optr = args[++i];
                else if (args[i].equals("-c")) // amt
                    optc = args[++i];
                else if (args[i].equals("-v"))
                    optv = true;
                else
                    throw new ArrayIndexOutOfBoundsException();
        } catch (ArrayIndexOutOfBoundsException e) {
            usage();
            return false;
        }
        if (optr != null)
            return true;
        else
            usage();
        return false;
    }

    private static void usage() {
        System.out
                .println("Usage: java spec.reporter.Reporter [options]\n"
                        + "Required options are:\n"
                        + "-r Results        A SPEC results file, generated by a benchmark run.\n"
                        + "                    May be in a mail message with mail headers.\n"
                        + "Other options are:\n"
                        + "-a                Plain ASCII text output\n"
                        + "                    default: generate HTML output with JPG graph\n"
                        + "-c              Second raw file, to compare\n"
                        + "                    default: none\n"
                        + "-e                Do NOT echo raw results properties in HTML output\n"
                        + "                    default: raw results inserted as HTML comments\n"
                        + "-h                Create graph in HTML rather than JPG\n"
                        + "                    default: use JPG if have Java2"
                        + "-l Label          Label to infix into the JPG name: SPECjbb.label.jpg\n"
                        + "                    default: a random number\n"
                        + "-o Output         Output file for generated HTML\n"
                        + "                    default: written to System.out\n"
                        + "-v                Verbose. List extra spec.testx.* properties\n"
                        + "                    default: extra properties are not reported\n");
    }
}

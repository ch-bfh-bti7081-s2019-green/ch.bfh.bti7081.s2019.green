package ch.bfh.bti7081.s2019.green.view.about;

import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "about", layout = DefaultRouterLayout.class)
@PageTitle("About")
public class AboutView extends VerticalLayout {
    public AboutView() {
        this.add(new H2("Project Team"));
        Paragraph teamParagraph = new Paragraph();
        teamParagraph.add(new Text("This project was created as a school project. The full source is "));
        teamParagraph.add(new Anchor("https://github.com/ch-bfh-bti7081-s2019-green/ch.bfh.bti7081.s2019.green", "on GitHub"));
        teamParagraph.add(new Text(". The following students implemented the project:"));
        teamParagraph.add(new ListItem(new Anchor("https://github.com/ChristianKocher7", "Christian Kocher")));
        teamParagraph.add(new ListItem(new Anchor("https://github.com/jmpout", "Fabio Caggiano")));
        teamParagraph.add(new ListItem(new Anchor("https://github.com/haesler", "Marc Häsler")));
        teamParagraph.add(new ListItem(new Anchor("https://mariusschaer.ch", "Marius Schär")));
        teamParagraph.add(new ListItem(new Anchor("https://severinkaderli.ch", "Severin Kaderli")));
        this.add(teamParagraph);
        this.add(new H2("Open Source Libraries"));
        Paragraph licenseParagraph = new Paragraph();
        licenseParagraph.add(new Text("We used the following libraries in our project:"));
        licenseParagraph.add(new ListItem(new Anchor("https://www.apache.org/licenses/LICENSE-2.0.txt", "Apache License 2.0")));
        licenseParagraph.add(new ListItem(new Anchor("https://www.apache.org/licenses/LICENSE-1.1.txt", "Apache License 1.1")));
        this.add(licenseParagraph);
        this.add(new Paragraph("App Layout Add-on (Apache 2)\n"));
        this.add(new Paragraph("Card (Apache 2)\n"));
        this.add(new Paragraph("Iron Collapse (Apache 2) "));
        this.add(new Paragraph("paper-ripple (Apache 2) "));
        this.add(new Paragraph("PaperMenuButton (Apache 2) "));
        this.add(new Anchor("http://www.antlr.org/", "AntLR Parser Generator (BSD License) "));
        this.add(new Anchor("http://github.com/FasterXML/java-classmate", "ClassMate (The Apache Software License, Version 2.0) "));
        this.add(new Anchor("https://github.com/gwenn/sqlite-dialect", "SQLite dialect for Hibernate (Public domain) "));
        this.add(new Anchor("http://github.com/DiUS/java-faker", "Java Faker (The Apache Software License, Version 2.0) "));
        this.add(new Anchor("https://github.com/mifmif/Generex/tree/master", "Generex (The Apache Software License, Version 2.0) "));
        this.add(new Anchor("http://findbugs.sourceforge.net/", "FindBugs-jsr305 (The Apache Software License, Version 2.0) "));
        this.add(new Anchor("http://www.h2database.com", "H2 Database Engine (MPL 2.0 or EPL 1.0) "));
        this.add(new Anchor("https://github.com/phax/ph-commons/ph-commons", "ph-commons (Apache 2) "));
        this.add(new Anchor("https://github.com/phax/ph-css/ph-css", "ph-css (Apache 2) "));
        this.add(new Anchor("https://github.com/swaldman/c3p0", "c3p0 (Eclipse Public License, Version 1.0) (GNU Lesser General Public License, Version 2.1) "));
        this.add(new Anchor("https://github.com/swaldman/mchange-commons-java", "mchange-commons-java (Eclipse Public License, Version 1.0) (GNU Lesser General Public License, Version 2.1) "));
        this.add(new Anchor("http://java.net/istack-commons/istack-commons-runtime/", "istack common utility code runtime (CDDL 1.1) (GPL2 w/ CPE) "));
        this.add(new Anchor("http://fi.java.net", "fastinfoset (Apache License, Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-client", "Flow Client (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-data", "Flow Data (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-html-components", "Flow HTML Components (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-push", "Flow Push (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-server", "Flow Server (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-accordion-flow-root/vaadin-accordion-flow", "Vaadin Accordion (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-app-layout-flow-root/vaadin-app-layout-flow", "Vaadin App Layout (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-button-flow-parent/vaadin-button-flow", "Vaadin Button (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-checkbox-flow-parent/vaadin-checkbox-flow", "Vaadin Checkbox (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-combo-box-flow-parent/vaadin-combo-box-flow", "Vaadin Combo Box (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-context-menu-flow-parent/vaadin-context-menu-flow", "Vaadin Context Menu (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com", "Vaadin Platform (vaadin-core) (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-custom-field-flow-parent/vaadin-custom-field-flow", "Vaadin CustomField (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-date-picker-flow-parent/vaadin-date-picker-flow", "Vaadin DatePicker (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-details-flow-parent/vaadin-details-flow", "Vaadin Details (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-dialog-flow-parent/vaadin-dialog-flow", "Vaadin Dialog Flow (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-form-layout-flow-parent/vaadin-form-layout-flow", "Vaadin Form Layout Flow (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-grid-flow-parent/vaadin-grid-flow", "Vaadin Grid (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-icons-flow-parent/vaadin-icons-flow", "Vaadin Icons (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-iron-list-flow-parent/vaadin-iron-list-flow", "Vaadin IronList (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-list-box-flow-parent/vaadin-list-box-flow", "Vaadin List Box (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-login-flow-parent/vaadin-login-flow", "Vaadin Login (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-theme-integrations/vaadin-lumo-theme", "Lumo theme for Flow (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-theme-integrations/vaadin-material-theme", "Material theme for Flow (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-notification-flow-parent/vaadin-notification-flow", "Vaadin Notification (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-ordered-layout-flow-parent/vaadin-ordered-layout-flow", "Vaadin Ordered Layouts (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-progress-bar-flow-parent/vaadin-progress-bar-flow", "Vaadin ProgressBar (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-radio-button-flow-parent/vaadin-radio-button-flow", "Vaadin Radio Button Flow (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-select-flow-parent/vaadin-select-flow", "Vaadin Select (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-split-layout-flow-parent/vaadin-split-layout-flow", "Vaadin Split Layout (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-tabs-flow-parent/vaadin-tabs-flow", "Vaadin Tabs Flow (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-text-field-flow-parent/vaadin-text-field-flow", "Vaadin TextField (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-time-picker-flow-parent/vaadin-time-picker-flow", "Vaadin TimePicker Flow (Apache License Version 2.0) "));
        this.add(new Anchor("http://vaadin.com/flow-component-base/vaadin-upload-flow-parent/vaadin-upload-flow", "Vaadin Upload (Apache License Version 2.0) "));
        this.add(new Anchor("http://code.google.com/p/gentyref/", "GenTyRef (The Apache Software License, Version 2.0) "));
        this.add(new Anchor("https://github.com/Atmosphere/atmosphere", "atmosphere-runtime (Apache License 2.0) (CDDL) "));
        this.add(new Anchor("http://vaadin.com/gwt-elemental", "Vaadin Customized GWT Elemental Library (Apache License Version 2.0) "));
        this.add(new Anchor("http://www.slf4j.org", "vaadin-slf4j-jdk14 (The MIT License) "));
        this.add(new Anchor("http://commons.apache.org/proper/commons-fileupload/", "Apache Commons FileUpload (Apache License, Version 2.0) "));
        this.add(new Anchor("http://commons.apache.org/proper/commons-io/", "Apache Commons IO (Apache License, Version 2.0) "));
        this.add(new Anchor("http://commons.apache.org/proper/commons-logging/", "Apache Commons Logging (The Apache Software License, Version 2.0) "));
        this.add(new Anchor("http://www.brics.dk/automaton/", "Automaton (BSD) "));
        this.add(new Anchor("http://java.net/all/javax.activation-api/", "JavaBeans Activation Framework API jar (CDDL/GPLv2+CE) "));
        this.add(new Anchor("https://github.com/javaee/jpa-spec", "javax.persistence-api (Eclipse Distribution License v. 1.0) (Eclipse Public License v1.0) "));
        this.add(new Anchor("http://servlet-spec.java.net", "Java Servlet API (CDDL + GPLv2 with classpath exception) "));
        this.add(new Anchor("http://beanvalidation.org", "Bean Validation API (Apache License 2.0) "));
        this.add(new Anchor("https://github.com/javaee/jaxb-spec/jaxb-api", "jaxb-api (CDDL 1.1) (GPL2 w/ CPE) "));
        this.add(new Anchor("http://junit.org", "JUnit (Eclipse Public License 1.0) "));
        this.add(new Anchor("http://logging.apache.org/log4j/1.2/", "Apache Log4j (The Apache Software License, Version 2.0) "));
        this.add(new Anchor("http://bytebuddy.net/byte-buddy", "Byte Buddy (without dependencies) (The Apache Software License, Version 2.0) "));
        this.add(new Anchor("http://bytebuddy.net/byte-buddy-agent", "Byte Buddy Java agent (The Apache Software License, Version 2.0) "));
        this.add(new Anchor("http://commons.apache.org/proper/commons-lang/", "Apache Commons Lang (Apache License, Version 2.0) "));
        this.add(new Anchor("http://dom4j.github.io/", "dom4j (BSD 3-clause New License) "));
        this.add(new Anchor("http://jaxb.java.net/jaxb-runtime-parent/jaxb-runtime", "JAXB Runtime (CDDL+GPL License) "));
        this.add(new Anchor("http://jaxb.java.net/jaxb-txw-parent/txw2", "TXW2 Runtime (CDDL+GPL License) "));
        this.add(new Anchor("https://github.com/hamcrest/JavaHamcrest/hamcrest-core", "Hamcrest Core (New BSD License) "));
        this.add(new Anchor("http://hibernate.org/orm", "Hibernate ORM - hibernate-c3p0 (GNU Library General Public License v2.1 or later) "));
        this.add(new Anchor("http://hibernate.org/orm", "Hibernate ORM - hibernate-core (GNU Library General Public License v2.1 or later) "));
        this.add(new Anchor("http://hibernate.org", "Hibernate Commons Annotations (GNU Library General Public License v2.1 or later) "));
        this.add(new Anchor("http://www.javassist.org/", "Javassist (Apache License 2.0) (LGPL 2.1) (MPL 1.1) "));
        this.add(new Anchor("http://www.jboss.org/jandex", "Java Annotation Indexer (Apache License, Version 2.0) "));
        this.add(new Anchor("http://www.jboss.org", "JBoss Logging 3 (Apache License, version 2.0) "));
        this.add(new Anchor("http://www.jboss.org/jboss-transaction-api_1.2_spec", "Java Transaction API (Common Development and Distribution License) (GNU General Public License, Version 2 with the Classpath Exception) "));
        this.add(new Anchor("https://jsoup.org/", "jsoup Java HTML Parser (The MIT License) "));
        this.add(new Anchor("http://stax-ex.java.net/", "Extended StAX API (Dual license consisting of the CDDL v1.1 and GPL v2) "));
        this.add(new Anchor("https://github.com/mockito/mockito", "mockito-core (The MIT License) "));
        this.add(new Anchor("http://objenesis.org", "Objenesis (Apache 2) "));
        this.add(new Anchor("http://ocpsoft.org/prettytime/prettytime/", "PrettyTime - Core (Apache License) "));
        this.add(new Anchor("https://github.com/pgjdbc/pgjdbc", "PostgreSQL JDBC Driver - JDBC 4.2 (BSD-2-Clause) "));
        this.add(new Anchor("https://projectlombok.org", "Project Lombok (The MIT License) "));
        this.add(new Anchor("http://www.slf4j.org", "SLF4J API Module (MIT License) "));
        this.add(new Anchor("http://www.slf4j.org", "SLF4J LOG4J-12 Binding (MIT License) "));
        this.add(new Anchor("https://spring.io/spring-security", "spring-security-crypto (The Apache Software License, Version 2.0) "));
        this.add(new Anchor("http://webjars.org", "polymer (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "app-layout (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "font-roboto (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-a11y-announcer (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-a11y-keys-behavior (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-behaviors (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-checked-element-behavior (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-collapse (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-dropdown (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-fit-behavior (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-flex-layout (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-form-element-behavior (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-icon (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-icons (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-iconset-svg (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-list (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-media-query (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-menu-behavior (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-meta (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-overlay-behavior (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-resizable-behavior (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-scroll-target-behavior (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-selector (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "iron-validatable-behavior (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "neon-animation (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "paper-badge (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "paper-behaviors (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "paper-icon-button (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "paper-menu-button (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "paper-ripple (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "paper-styles (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "paper-tabs (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "vaadin-accordion (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-app-layout (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-button (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-checkbox (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-combo-box (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-context-menu (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-control-state-mixin (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-custom-field (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-date-picker (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-details (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-development-mode-detector (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-dialog (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-element-mixin (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-form-layout (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-grid (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-icons (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-item (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-list-box (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-list-mixin (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-login (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-lumo-styles (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-material-styles (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-notification (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-ordered-layout (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-overlay (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-progress-bar (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-radio-button (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-select (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-split-layout (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-tabs (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-text-field (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-themable-mixin (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-time-picker (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-upload (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "vaadin-usage-statistics (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "web-animations-js (Apache-2.0) "));
        this.add(new Anchor("http://webjars.org", "shadycss (BSD 3-Clause) "));
        this.add(new Anchor("http://webjars.org", "webcomponentsjs (BSD 3-Clause) "));
        this.add(new Anchor("http://nexus.sonatype.org/oss-repository-hosting.html/sqlite-jdbc", "SQLite JDBC (The Apache Software License, Version 2.0) "));
        this.add(new Anchor("http://www.snakeyaml.org", "SnakeYAML (Apache License, Version 2.0) "));
    }
}

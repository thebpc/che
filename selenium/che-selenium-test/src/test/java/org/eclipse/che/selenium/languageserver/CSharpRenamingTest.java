package org.eclipse.che.selenium.languageserver;

import static org.eclipse.che.selenium.core.project.ProjectTemplates.DOT_NET;

import com.google.inject.Inject;
import java.net.URL;
import java.nio.file.Paths;
import org.eclipse.che.commons.lang.NameGenerator;
import org.eclipse.che.selenium.core.SeleniumWebDriver;
import org.eclipse.che.selenium.core.client.TestCommandServiceClient;
import org.eclipse.che.selenium.core.client.TestProjectServiceClient;
import org.eclipse.che.selenium.core.workspace.InjectTestWorkspace;
import org.eclipse.che.selenium.core.workspace.TestWorkspace;
import org.eclipse.che.selenium.core.workspace.WorkspaceTemplate;
import org.eclipse.che.selenium.pageobject.CodenvyEditor;
import org.eclipse.che.selenium.pageobject.Consoles;
import org.eclipse.che.selenium.pageobject.Ide;
import org.eclipse.che.selenium.pageobject.Loader;
import org.eclipse.che.selenium.pageobject.Menu;
import org.eclipse.che.selenium.pageobject.ProjectExplorer;
import org.eclipse.che.selenium.pageobject.Wizard;
import org.eclipse.che.selenium.pageobject.intelligent.CommandsPalette;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CSharpRenamingTest {
  private final String PROJECT_NAME =
      NameGenerator.generate(CSharpRenamingTest.class.getSimpleName(), 4);

  @InjectTestWorkspace(template = WorkspaceTemplate.UBUNTU_LSP)
  private TestWorkspace workspace;

  @Inject private Ide ide;
  @Inject private ProjectExplorer projectExplorer;
  @Inject private Loader loader;
  @Inject private CodenvyEditor editor;
  @Inject private Menu menu;
  @Inject private Wizard wizard;
  @Inject private SeleniumWebDriver seleniumWebDriver;
  @Inject private TestCommandServiceClient testCommandServiceClient;
  @Inject private CommandsPalette commandsPalette;
  @Inject private Consoles consoles;
  @Inject private TestProjectServiceClient testProjectServiceClient;

  @BeforeClass
  public void setUp() throws Exception {
    URL resource = getClass().getResource("/projects/CsharpHelloWorld");
    testProjectServiceClient.importProject(
        workspace.getId(), Paths.get(resource.toURI()), PROJECT_NAME, DOT_NET);
    ide.open(workspace);
  }

  @Test
  public void checkRenaming() {
    projectExplorer.openItemByPath(PROJECT_NAME);
  }
}
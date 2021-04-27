# testing-best-practices
Testing best practices curated by industry's best so that we can all enjoy better tech.🚀

## `Table of Contents`

### [`What is a good test?`](#what-is-a-good-test)
### [`Testing Web Applications`](#testing-web-applications)
* [Testing a button](#testing-a-button)
* [Testing a link](#testing-a-link)
### [`Best Resources To Learn Testing`](#best-resources-to-learn-testing)
### [`X: React Testing Library`](#section-)

# What is a good test?

[These properties were originally proposed by Kent Beck](https://medium.com/@kentbeck_7670/test-desiderata-94150638a4b3)

* Isolated — tests should return the same results regardless of the order in which they are run.
* Composable — if tests are isolated, then I can run 1 or 10 or 100 or 1,000,000 and get the same results.
* Fast — tests should run quickly.
* Inspiring — passing the tests should inspire confidence
* Writable — tests should be cheap to write relative to the cost of the code being tested.
* Readable — tests should be comprehensible for reader, invoking the motivation for writing this particular test.
* Behavioral — tests should be sensitive to changes in the behavior of the code under test. If the behavior changes, the test result should change.
* Structure-insensitive — tests should not change their result if the structure of the code changes.
* Automated — tests should run without human intervention.
* Specific — if a test fails, the cause of the failure should be obvious.
* Deterministic — if nothing changes, the test result shouldn’t change.
* Predictive — if the tests all pass, then the code under test should be suitable for production.

# Testing Web Applications

The modern web application typically runs on some type of JavaScript framework such as Angular, React, or Vue. The guiding principle of testing such web applications is that we rarely require a browser to test them. A combination of component, unit, and visual tests will be the most powerful in ensuring quality web applications.


## Testing a button

✅ **Do**: Use a component test with a virtual DOM instead of an actual browser


❌ **Otherwise**: Extra dependencies will create more complications and work, with no added benefit:

⚠️ Need a browser

⚠️ Need a server

⚠️ Need to deal with network issues

⚠️ Test will be slower

⚠️ Need an extra dependency (Cypress/Selenium...)

⚠️ Need to learn extra dependency API

<details><summary>✏ <b>Code Examples</b></summary>
  
<br/>
  
### :clap: Doing It Right Example: A button should be tested with a component test


```javascript
// App.js - the button being tested
function App() {
  const [buttonColor, setButtonColor] = useState('red');
  const newButtonColor = buttonColor === 'red' ? 'blue' : 'red';
  return (
    <div>
      <button 
        style={{backgroundColor: buttonColor}}
        onClick={() => setButtonColor(newButtonColor)}>
          Change to {newButtonColor} 
      </button>
    </div>
  );
}

```

**Expected Behaviors**
1. Button starts in 'red' state
2. Button starts with text that says "Change to blue"
3. On click, button changes color to 'blue'
4. On click, button changes text that says "Change to red"

```javascript
import { render, screen, fireEvent } from '@testing-library/react';
import App from './App';

test('should start in red state', () => {
  //render the component in a virtual DOM
  render(<App />);
  //find an element with a role of button and text
  const button = screen.getByRole('button', {name: 'Change to blue'})
  //expect the background color to be red
  expect(button).toHaveStyle({backgroundColor: 'red'})
});

test('should change color to blue on click', () => {
  //render the component in a virtual DOM
  render(<App />);
  //find an element with a role of button and text
  const button = screen.getByRole('button', {name: 'Change to blue'})
  //click button
  fireEvent.click(button);
  //expect the background color to be blue after click
  expect(button).toHaveStyle({backgroundColor: 'blue'})
  //check that text is changed to 'Changed to red'
  expect(button.textContent).toBe('Change to red')
})
```

<br/>

</details>

<br/>
<details><summary>© <b>Credits & read-more</b></summary>
  1. <a href='https://github.com/nadvolod/js-code/blob/1b6b15a79349748a40fc630d4d8de699ffd50780/testing-js/react-components/test-app/src/App.test.js#L4'>Nikolay Advolodkin - testing a button component</a>
</details>

<br/><br/>

## Testing a link

✅ **Do**: Use a component test with a virtual DOM instead of an actual browser.

Avoid clicking a link as the clickability of `<a>` is a default browser behavior and not the behavior of our SUT (Software Under Test).

❌ **Otherwise**: Extra dependencies will create more complications and work, with no added benefit. Same problems as a button test.

<details><summary>✏ <b>Code Examples</b></summary>
  
<br/>
  
### :clap: Doing It Right Example: A link should be tested with a component test

**SUT**

```javascript
// App.js - the link being tested
function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://www.ultimateqa.com"
          target="_blank"
          rel="noopener noreferrer"
          data-testid="learn-link"
        >
          Learn Testing with Mia
        </a>
      </header>
    </div>
  );
}

```

**Rendered View**

**Expected Behaviors**
1. Link goes to "https://www.ultimateqa.com"
2. Link opens in new tab

```javascript

```

<br/>

</details>

<br/>
<details><summary>© <b>Credits & read-more</b></summary>
  1. <a href='https://github.com/nadvolod/js-code/blob/1b6b15a79349748a40fc630d4d8de699ffd50780/testing-js/react-components/test-app/src/App.test.js#L4'>Nikolay Advolodkin - testing a button component</a>
</details>

<br/><br/>



## ⚪ ️ Testing a CAPTCHA

CAPTCHA is short for "Completely Automated Public Turing test to tell Computers and Humans Apart." It exists to prevent automation, so it's not even worth trying. Otherwise, it fails to be CAPTCHA, because it won’t be able to tell humans and computers apart ([Selenium.dev](https://www.selenium.dev/documentation/en/worst_practices/captchas/)).

Two primary strategies exist to get around CAPTCHA checks during testing. They are as follows:

* Disable CAPTCHAs in your test environment. This could be a simple configuration in the software under test. Or this can even be a URL parameter that the test sets. Example: `www.software.com/?disableCaptcha`
* Add a hook to allow tests to bypass the CAPTCHA.

([10 testing scenarios you should never automate with Selenium. TechBeacon.com](https://techbeacon.com/app-dev-testing/10-testing-scenarios-you-should-never-automate-selenium))

## ⚪ ️ Doing visual testing by large scale element identification

https://techbeacon.com/app-dev-testing/10-testing-scenarios-you-should-never-automate-selenium

## ⚪ ️ Two-factor authentication

https://techbeacon.com/app-dev-testing/10-testing-scenarios-you-should-never-automate-selenium

## ⚪ ️ File downloads

https://techbeacon.com/app-dev-testing/10-testing-scenarios-you-should-never-automate-selenium

## ⚪ ️ HTTP reponse codes

https://techbeacon.com/app-dev-testing/10-testing-scenarios-you-should-never-automate-selenium

## ⚪ ️ Gmail, email, and Facebook logins

https://techbeacon.com/app-dev-testing/10-testing-scenarios-you-should-never-automate-selenium

## ⚪ ️ Stress or load testing

https://techbeacon.com/app-dev-testing/10-testing-scenarios-you-should-never-automate-selenium

## ⚪ ️ Testing links

https://techbeacon.com/app-dev-testing/10-testing-scenarios-you-should-never-automate-selenium
https://github.com/saucelabs-training/automation-best-practices/blob/9cea9cb0af39de3adc91dd42e89d68a9a1a5df96/my-react-app/src/__tests__/Solution.test.js#L16

## ⚪ ️ Testing video streaming

https://techbeacon.com/app-dev-testing/10-testing-scenarios-you-should-never-automate-selenium

## ⚪ ️ Testing tabs

https://github.com/saucelabs-training/automation-best-practices/blob/9cea9cb0af39de3adc91dd42e89d68a9a1a5df96/my-react-app/src/__tests__/Solution.test.js#L25


# Best Resources To Learn Testing

## ⚪ ️ Unit testing
## ⚪ ️ Integration testing
## ⚪ ️ Component testing
<a href="https://click.linksynergy.com/link?id=fVAbrMnoMS0&offerid=507388.3780436&type=2&murl=https%3A%2F%2Fwww.udemy.com%2Fcourse%2Freact-testing-library%2F">Testing React with Jest and Testing Library (Udemy course)</a><IMG border=0 width=1 height=1 src="https://ad.linksynergy.com/fs-bin/show?id=fVAbrMnoMS0&bids=507388.3780436&type=2&subid=0" >
## ⚪ ️ Acceptance testing
### ⚪ ️ Browser testing
  
### ⚪ ️ Visual testing
### ⚪ ️ Front-end performance testing
### ⚪ ️ Back-end performance testing
### ⚪ ️ Security testing

# X: React Testing Library


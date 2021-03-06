/*
 * Copyright © 2015 Stefan Niederhauser (nidin@gmx.ch)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package guru.nidi.graphviz.model;

import guru.nidi.graphviz.attribute.Attributes;
import guru.nidi.graphviz.attribute.Label;

import java.util.ArrayList;
import java.util.List;

class ImmutableNode extends MutableNode implements Node {
    ImmutableNode(Label name) {
        this(name, new ArrayList<>(), Attributes.attrs());
    }

    private ImmutableNode(Label name, List<Link> links, Attributes attributes) {
        super(name, links, attributes);
    }

    public PortNode port() {
        return port(null, null);
    }

    public PortNode port(String record) {
        return port(record, null);
    }

    public PortNode port(Compass compass) {
        return port(null, compass);
    }

    public PortNode port(String record, Compass compass) {
        return new ImmutablePortNode(this, record, compass);
    }

    public ImmutableNode link(LinkTarget target) {
        return (ImmutableNode) copyOfMut().addLink(target);
    }

    public ImmutableNode link(LinkTarget... targets) {
        return (ImmutableNode) copyOfMut().addLink(targets);
    }

    public ImmutableNode link(String node) {
        return (ImmutableNode) copyOfMut().addLink(node);
        //         return update(copyOfMut().addLink(node));
    }

    public ImmutableNode link(String... nodes) {
        return (ImmutableNode) copyOfMut().addLink(nodes);
        //        return update(copyOfMut().addLink(nodes));
    }

    public ImmutableNode with(Attributes attrs) {
        return (ImmutableNode) copyOfMut().add(attrs);
    }

    private ImmutableNode copyOfMut() {
        return new ImmutableNode(name, new ArrayList<>(links), attributes.applyTo(Attributes.attrs()));
    }

//    private ImmutableNode update(MutableNode node) {
//        final ImmutableNode n = (ImmutableNode) node;
//        CreationContext.current().map(ctx -> ctx.setNode(n));
//        return n;
//    }
}
